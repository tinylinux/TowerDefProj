import Math._
import scala.collection.mutable.Buffer
import scala.collection.mutable.HashMap
import scala.swing._
import scala.swing.BorderPanel.Position._
import javax.swing.BorderFactory._
import java.awt.{ Color, Font }
import java.lang.System
import event._

/****************************************************************************/

case class leftClicked (o:Object) extends Event
case class moveTo (c:Cell) extends Event
case class invUse (i:Item) extends Event

/****************************************************************************/

class Symbol (val form:Char, val color:Color) { }

/****************************************************************************/

// Message panel at top of screen. Can add multiple sentences to it.

object Messages extends Label
{
	preferredSize = new Dimension(0,25)
	font = new Font("default",0,18)

	def empty { text = "" }

	def add (s:String)
	{
		if (text == "") text = s
		else text += " " + s
	}

	// create a sentence in 2nd or 3rd person
	def addVerb (a:Actor, verb:String, rest:String) : Unit =
	{
		val name = a.toString.capitalize + " "
		val nVerb = name + (if (a.isHero) verb else verb+"s")
		add(nVerb + (if (rest.length > 0) " "+rest else "")+".")
	}
}

// Inventory list at bottom of screen.
// Maintains hashmaps between items and corresponding buttons. Click events are
// sent to Castle object. If inventory empty, keep one button saying "empty".

object Inventory extends BorderPanel
{
	class invButton(t:String) extends Button
	{
		preferredSize = new Dimension(50,50)
		listenTo(mouse.clicks)
		text = t

		reactions +=
		{
			case MouseClicked(_, _ ,0, _ , _ ) =>
				{ publish(leftClicked(this)) }
		}
	}

	val invLabel = new Label { text = "Inventory" }
	val items = new GridPanel(1,5)
	val itemToButton = new HashMap[Item,invButton]
	val buttonToItem = new HashMap[invButton,Item]

	val emptyButton = new invButton("empty")

	// create a button for a new item
	def addItem (item:Item) : Unit =
	{
		items.contents -= emptyButton
		val button = new invButton(item.symbol.form.toString
						+ " " + item.name)
		items.contents += button

		listenTo(button)
		itemToButton += (item -> button)
		buttonToItem += (button -> item)
	}

	// remove the button for a certain item
	def removeItem (item:Item) : Unit =
	{
		val button = itemToButton(item)
		items.contents -= button
		itemToButton -= item
		buttonToItem -= button

		if (itemToButton.size == 0) items.contents += emptyButton
	}

	layout(invLabel) = North
	layout(items) = Center
	items.contents += emptyButton

	reactions += { case leftClicked(button:invButton)
				=> publish(invUse(buttonToItem(button))) }
}

/****************************************************************************/

abstract class Actor
{
	var position : Cell = null
	def symbol : Symbol

	var life = 1
	def isHero : Boolean = { return false }

	def name : String
	override def toString : String = { if (isHero) "you" else "the "+name }

	def placeOnMap (c:Cell) { position = c; c.setActor(this) }

	// react to music, do nothing by default
	def hear : Unit = { }

	// take a hit from another actor, possibly die
	def hit
	{
		life -= 1; if (life > 0) return
		Messages.addVerb(this,"die","")
		position.removeActor
		(new Corpse(this)).placeOnMap(position)
	}

	///////////////////////////////////////////////////////////////
	// Movement-related code: Castle object will call move(), which
	// calls react() as a function of the actor's speed; react()
	// is supposed to contain the actor-specific code.
	// move and react return true if something "important" happens.

	var speed = 100		// speed relative to player
	var residualSpeed = 0

	def move : Boolean =
	{
		var retval = false
		residualSpeed += speed
		while (residualSpeed >= 100)
			{ residualSpeed -= 100; retval = react || retval }
		return retval
	}

	// by default, do nothing
	def react : Boolean = { return false }
}

class Hero extends Actor
{
	val symbol = new Symbol('♣',Color.black)
	override val name = "you"
	var hasWeapon = false
	override def isHero : Boolean = { return true }

	life = 5

	def take (item:Item) : Boolean =
	{
		// we just take everything for the moment
		item.position.removeItem
		Inventory.addItem(item)

		Messages.add("You take the " + item.name + ".")

		item match { case s:Sword => hasWeapon = true; case _ => {} }

		return item.pickedUpBy(this)
	}

	def attack (actor:Actor) : Boolean =
	{
		if (!hasWeapon)
			Messages.add("You do not have a weapon.")
		else
		{
			Messages.add("You hit " + actor + ".")
			actor.hit
		}
		return true
	}
}

abstract class AgressiveActor extends Actor
{
	var enemy:Actor = null

	def setEnemy (a:Actor) = { enemy = a }

	// go after the enemy and attack if the enemy is next to the actor
	def pursuit : Boolean =
	{
		if (enemy == null) return false
		if (enemy.life == 0) { enemy = null; return false }

		val next = position.room.cells.neighbours(position).
					minBy(_.l2dist(enemy.position))

		if (next.actor == null)
			{ next.floor.enter(this,next); return false }
		
		if (next.actor == enemy)
		{
			Messages.addVerb(this,"hit",enemy.toString)
			enemy.hit
			return true
		}

		return false
	}
}

class Fairy extends Actor
{
	val symbol = new Symbol('F',Color.green)
	override val name = "fairy"

	override def hear : Unit =
	{
		Messages.add("The fairy likes the music and moves aside.")
		position.removeActor
	}
}

class Gnome extends AgressiveActor
{
	val symbol = new Symbol('G',Color.magenta)
	override val name = "gnome"
	life = 3
	speed = 70

	override def hear : Unit =
	{
		Messages.add("The gnome covers his ears in dismay.")
	}

	override def react : Boolean = { return pursuit }
}

/****************************************************************************/

abstract class Floor
{
	def color:Color

	// true if entering ought to stop auto-move; block by default
	def enter(actor:Actor,cell:Cell) : Boolean = { return true }
}

// Wall and Ground are a single object to avoid instantiating one Floor
// object for each cell.

object Wall extends Floor { val color = Color.darkGray }

object Ground extends Floor
{
	val color = Color.white

	// no problem entering here
	override def enter(actor:Actor,cell:Cell) : Boolean =
	{
		// move the actor
		actor.position.removeActor
		actor.placeOnMap(cell)
		return false
	}
}

class Teleporter (target:Cell) extends Floor
{
	val color = Color.pink

	// move actor to random other position
	override def enter(a:Actor,c:Cell) : Boolean =
	{
		Messages.addVerb(a,"step","into the teleporter")
		if (!target.isFree)
		{
			Messages.add("For some reason, nothing happens.")
			return a.isHero
		}
		a.position.removeActor
		a.placeOnMap(target)
		return a.isHero
	}
}

/****************************************************************************/

abstract class Item
{
	def symbol : Symbol
	var owner : Actor = null
	var position : Cell = null
	def name : String
	def use : Unit

	// pick up item, return true if anything special happens
	def pickedUpBy (a:Actor) : Boolean =
	{
		if (position != null) position.removeItem
		position = null
		owner = a
		return false
	}

	def placeOnMap (c:Cell) { position = c; owner = null; c.setItem(this) }
}

class Harp extends Item
{
	val symbol = new Symbol('♫',Color.blue)
	val name = "harp"

	def use : Unit =
	{
		Messages.add("You play on the harp.")

		// make all actors in distance 3 listen to music
		owner.position.room.cells.
			filter(_.l2dist(owner.position) <= 3).
			filter(_.actor != null).map(_.actor.hear)
	}
}

class Sword extends Item
{
	val symbol = new Symbol('┼',Color.darkGray)
	val name = "sword"
	def use : Unit = { Messages.add("Just click on a monster to attack.") }
}

class Potion extends Item
{
	val symbol = new Symbol('!',Color.blue)
	val name = "potion"
	def use : Unit =
	{
		owner.life += 1
		Messages.addVerb(owner,"drink","the potion")
		if (owner.isHero) Messages.add("You feel better.")
		if (owner.isHero) Inventory.removeItem(this)
	}
}

class Corpse (actor:Actor) extends Item
{
	val symbol = new Symbol('%',actor.symbol.color)
	val name = actor.name+" corpse"
	def use : Unit = {
		Messages.add("What on earth do you want to do with this?")
	}
}

/****************************************************************************/

// This class represents one dungeon cell with a floor, optionally an actor
// and an item. Handles the visual appearance and user-interface aspects.

class Cell (val room:Room, val y:Int, val x:Int) extends Button
{
	var floor : Floor = null
	var actor : Actor = null
	var item : Item = null

	def setFloor (f:Floor) = { floor = f; update }
	def removeActor () = { actor = null; update }
	def setActor (a:Actor) { actor = a; update }
	def removeItem () = { item = null; update }
	def setItem (i:Item) { item = i; update }

	// number of moves towards c
	def linfdist (c:Cell) = max(abs(c.x-x),abs(c.y-y))

	// Euclidian distance towards c
	def l2dist (c:Cell) = sqrt((c.x-x)*(c.x-x)+(c.y-y)*(c.y-y))

	// Return whether the cell has a normal floor and no actor or item.
	def isFree : Boolean =
	{
		if (actor != null) return false
		if (item != null) return false
		floor match { case Ground => return true }
		return false
	}

	// visual appearance
	border = createEmptyBorder
	font = new Font("default",0,25)
	preferredSize = new Dimension(30,30)
	focusPainted = false

	def update
	{
		def copySymbol (symbol:Symbol)
		{
			foreground = symbol.color
			text = symbol.form.toString
		}

		text = ""; background = floor.color
		if (item != null) copySymbol(item.symbol)
		if (actor != null) copySymbol(actor.symbol)
	}

	// user interface
	listenTo(mouse.clicks)

	reactions +=
	{
		case MouseClicked(_, _ ,0, _ , _ ) =>
			{ publish(leftClicked(this)) }
		case UIElementResized(_) =>
			font = new Font("default",Font.BOLD,
				min(size.height,size.width)*4/5)
	}

}

// Handle a grid of dungeon cells, facilitating some aggregate functions
class Grid (room:Room, rows:Int, cols:Int)
{
	val elem = IndexedSeq.tabulate(rows,cols) {(i,j) => new Cell(room,i,j)}
	def map[U](f:Cell=>U) = elem.map(_.map(f(_)))
	def filter(f:Cell=>Boolean) = elem.flatten.filter(f(_))
	def apply (i:Int,j:Int) = elem(i)(j)
	def neighbours(c:Cell) = elem.flatten.filter(c.linfdist(_) <= 1)
}

/****************************************************************************/

// We only ever instantiate one single room, so some of the code here
// is overkill.

class Room (val castle:Castle, val cols:Int, val rows:Int)
	extends Reactor with Publisher
{
	var cells = new Grid(this,rows,cols)

	cells.map(_.setFloor(Ground))
	cells.map(listenTo(_))

	reactions +=
	{
		case leftClicked(c:Cell) => { publish(moveTo(c)) }
	}

	def makeWall (c:Cell, d:Cell)
	{
		for (x <- c.x to d.x; y <- c.y to d.y)
			{ cells(y,x).setFloor(Wall) }
	}
}

class PlainRoom (castle:Castle, cols:Int, rows:Int)
	extends Room (castle,cols,rows)
{
	makeWall(cells(0,0),cells(0,cols-1))
	makeWall(cells(0,0),cells(rows-1,0))
	makeWall(cells(rows-1,0),cells(rows-1,cols-1))
	makeWall(cells(0,cols-1),cells(rows-1,cols-1))
}

/****************************************************************************/

// Castle coordinates the gameplay. Mover is a thread that takes a target
// for the player and makes one move towards that target every 100ms. Mover
// stops if something "important" happens in between (such as a monster
// hitting the player), which is why a lot of the functions in the actor/
// item/floor code returns booleans.

class Castle extends Reactor
{
	var cols = 24
	val rows = 18

	val hero = new Hero

	object Mover extends Runnable
	{
		var target : Cell = null

		def set_target (cell:Cell)
		{
			this.synchronized { target = cell }
		}

		def nextMove : Unit =
		{
			val hp = hero.position
			if (target == hp) return

			// find neighbour cell closest to target
			val neighbours = room.cells.neighbours(hp)
			val next = neighbours.minBy(_.l2dist(target))

			// try to move, and stop if something special happened
			if (moveHero(next)) target = hero.position

			// now it's the monsters turn
			if (moveMonsters) target = hero.position

			checkHeroAlive
		}

		def run
		{
			while (true)
			{
				this.synchronized { nextMove }
				Thread.sleep(100)
			}
		}
	}

	// Setting up the playing arena (except the hero, which is 
	// placed later due to interference with Mover code).
	val room = new PlainRoom (this,cols,rows)
	{
		cells(5,10).setFloor(Wall)
		cells(5,11).setFloor(Wall)
		cells(5,12).setFloor(Wall)
		cells(14,10).setFloor(Wall); cells(14,12).setFloor(Wall)
		cells(15,10).setFloor(Wall); cells(15,12).setFloor(Wall)
		cells(16,10).setFloor(Wall); cells(16,12).setFloor(Wall)

		cells(6,3).setFloor(new Teleporter(cells(13,20)))
		cells(6,20).setFloor(new Teleporter(cells(13,3)))

		(new Harp).placeOnMap(cells(3,7))
		(new Sword).placeOnMap(cells(16,11))
		(new Potion).placeOnMap(cells(9,6))
		(new Potion).placeOnMap(cells(15,16))

		(new Fairy).placeOnMap(cells(14,11))
		(new Gnome { setEnemy(hero) }).placeOnMap(cells(9,16))
		(new Gnome { setEnemy(hero) }).placeOnMap(cells(15,2))
	}

	// Change hero position (e.g. hero enters new room), where no
	// reaction from other actors is desired.
	def resetHero (cell:Cell)
	{
		hero.placeOnMap(cell)
		Mover.set_target(cell)
	}

	// A turn where the hero tries to move, return true on important event.
	def moveHero (to:Cell) : Boolean =
	{
		Messages.empty

		// some other actor is already there
		if (to.actor != null) { hero.attack(to.actor); return true }

		// if there is an item, pick it up but stop only if there
		// was something special
		if (to.item != null && hero.take(to.item)) return true

		// actually go there
		return to.floor.enter(hero,to);
	}

	// A turn where the hero uses an item, return true on important event.
	def useItem (item:Item)
	{
		Messages.empty
		item.use
		moveMonsters
		checkHeroAlive
	}

	// Play all the monsters' turns, return true on important event.
	def moveMonsters : Boolean =
	{
		val actors = room.cells.filter(_.actor != null).map(_.actor)
		val stopped = actors.map(a => if (a.life > 0) a.move else false)
		return stopped.exists(b=>b)
	}

	// If player died during move, disable the buttons.
	def checkHeroAlive
	{
		if (hero.life > 0) return
		deafTo(room); deafTo(Inventory)
	}

	// Set up the elements of the user interface.
	def newGame : BorderPanel =
	{
		Messages.add("Welcome to the castle!")

		val grid = new GridPanel(rows,cols)
		room.cells.map(grid.contents += _)

		resetHero(room.cells(9,12))
		new Thread(Mover).start

		listenTo(room); listenTo(Inventory)

		val panel = new BorderPanel;
		panel.layout(grid) = Center
		panel.layout(Messages) = North
		panel.layout(Inventory) = South
		return panel
	}

	// User clicks on dungeon cell or item button
	reactions +=
	{
		case moveTo(c:Cell) => { Mover.set_target(c) }
		case invUse(i:Item) => { useItem(i) }
	}
}

/****************************************************************************/

object main extends SimpleSwingApplication
{
	val top = new MainFrame
	{
		title = "Castle"
		contents = (new Castle).newGame
		centerOnScreen()
	}
}
