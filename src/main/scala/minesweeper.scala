import Math._
import scala.util.Random
import scala.collection.mutable.Buffer
import scala.swing._
import scala.swing.BorderPanel.Position._
import javax.swing.BorderFactory._
import javax.swing.border.Border
import java.awt.{ Color, Font }
import event._

// Un simple Minesweeper (+ un jeu supplÃ©mentaire) qui utilise Swing.
// Il n'y a pas trop de commentaires, dÃ©solÃ©...

class Parameter (val name:String, var value:Int, set:(Int)=>Unit,
			minimum:Int = 0, maximum:Int = Int.MaxValue)
{
	val label = new Label(name)
	val field = new TextField { text = value.toString }
	def setparm  =
	{
		try { set(min(maximum,max(minimum,field.text.toInt))) }
		catch { case ex: Exception => { } }
	}
}

class CustomDialog (val params:List[Parameter]) extends Dialog
{
	title = "Custom Settings"
	modal = true

	contents = new BorderPanel
	{
		layout(new BoxPanel(Orientation.Vertical) {
			border = Swing.EmptyBorder(10)
			params.map(p => { contents += p.label;
					 contents += p.field })
		}) = Center

		layout(new FlowPanel() {
			contents += Button("Ok")
			{
				params.map(_.setparm)
				close()
			}
		}) = South
	}

	centerOnScreen()
	open()
}

case class leftClicked (c:Cell) extends Event
case class rightClicked (c:Cell) extends Event

class Cell (val i:Int, val j:Int) extends Button
{
	class CellState
	{
		var text = ""
		var fgColor = foreground
		var bgColor = background
		var border = createRaisedBevelBorder
	}

	def setState (cs:CellState) =
	{
		text = cs.text; border = cs.border
		foreground = cs.fgColor; background = cs.bgColor
	}

	var initial = new CellState
	var terminal = new CellState
	val defaultColor = background

	def reset = setState(initial)
	def reveal = setState(terminal)
	def setText (s:String) = { text = s }
	def setBorder (b:Border) = { border = b }
	def setFg (c:Color) = { foreground = c }
	def setBg (c:Color) = { background = c }

	def l1dist (c:Cell) = max(abs(c.i-i),abs(c.j-j))

	preferredSize = new Dimension(30,30)
	focusPainted = false
	listenTo(mouse.clicks)

	reactions +=
	{
		case MouseClicked(_, _ ,0, _ , _ ) =>
			publish(leftClicked(this))
		case MouseClicked(_, _ ,256, _ , _ ) =>
			publish(rightClicked(this))
		case UIElementResized(_) =>
			font = new Font("default",Font.BOLD,
				min(size.height,size.width)*4/5)
	}
}

class Grid[+T] (rows:Int, cols:Int, gen:(Int,Int)=>T)
{
	val elem = IndexedSeq.tabulate[T](rows,cols) { (i,j) => gen(i,j) }
	def map[U](f:(T)=>U) = elem.map(_.map(f(_)))
	def filter(f:(T)=>Boolean) = elem.flatten.filter(f(_))
	def count(f:(T)=>Boolean) = elem.flatten.count(f(_))
	def row(i:Int) = elem(i)
	def column(i:Int) = elem.map(e => e(i))
	def apply (i:Int,j:Int) = elem(i)(j)
}

abstract class Game extends Reactor
{
	def name : String
	def cells : Grid[Cell]
	def generate : Unit
	def reset : Unit

	val menu = new Menu("Parameters")
	var rows : Int = 0
	var cols : Int = 0

	def randomRow = Random.nextInt(rows)
	def randomCol = Random.nextInt(cols)
	def draw[T] (buf:Buffer[T]) = buf.remove(Random.nextInt(buf.size))

	def addParams (a:Action) =
	{
		menu.contents += new MenuItem(Action(a.title)
			{ a.apply(); main.newGame() } )
	}

	def defaultParams (a:Action) =
	{
		a.apply();
		addParams(a)
	}

        val messages = new Label
        {
		preferredSize = new Dimension(0,25)
		font = new Font("default",0,18)
		def update (s:String) = { text = s }
		def empty = { text = "" }
        }

	def loseGame = { messages.update("You've lost!"); stopGame }
	def winGame = { messages.update("You've won!"); stopGame }

	def newGame () : BorderPanel =
	{
		val grid = new GridPanel(rows,cols)
		val panel = new BorderPanel;
		panel.layout(grid) = Center
		panel.layout(messages) = North
		generate
		cells.map(grid.contents += _)
		restart
		return panel
	}

	def restart () : Unit =
	{
		reset
		cells.map(_.reset)
		cells.map(listenTo(_))
	}

	def solve () : Unit =
	{
		cells.map(_.reveal);
		messages.empty
		stopGame
	}

	def stopGame () = cells.map(deafTo(_))
}

object Minesweeper extends Game
{
	var name = "Minesweeper"
	var mines = 0
	var minesUsed = 0
	var toOpen = 0
	var minesLeft = 0

	var cells : Grid[MineCell] = null
	var freecell : MineCell = null

	class MineCell (i:Int,j:Int) extends Cell(i,j)
	{
		var isMine = false
		var nbs:Int = 0
		var opened = false
		var isFlagged = false
		val blank = new Color(240,240,240)
		def colors = Array(Color.black, Color.blue, new Color(0,204,0),
				Color.red, Color.magenta, Color.orange,
				Color.cyan, Color.black, Color.lightGray)

		def defStates
		{
			initial.text =
				(if (this == freecell) "x" else "")
			terminal.text =
				(if (isMine) "*" else
					if (nbs > 0) nbs.toString else "")
			terminal.fgColor =
				(if (isMine) Color.black else colors(nbs))
			terminal.bgColor = (if (isMine) defaultColor else blank)
		}

		def neighbours () : IndexedSeq[MineCell] =
			(for (k <- max(i-1,0) to min(i+1,rows-1);
				l <- max(j-1,0) to min(j+1,cols-1)
				if (k,l) != (i,j)) yield cells(k,l))

		override def reset { super.reset; opened = false;
						isFlagged = false }
		override def reveal { super.reveal; opened = true }
	}

	def generate : Unit =
	{
		cells = new Grid[MineCell](rows,cols,(i,j) => new MineCell(i,j))
		freecell = cells(randomRow,randomCol)
		var buf = cells.filter(_.l1dist(freecell) > 1).toBuffer
		minesUsed = min(mines,buf.size)
		
		for (i <- 1 to minesUsed) draw(buf).isMine = true

		cells.map(c => c.nbs = c.neighbours.count(_.isMine))
		cells.map(_.defStates)
	}

	def msgUpdate = { messages.update(minesLeft + " mines left") }

	def reset : Unit =
	{
		minesLeft = minesUsed
		toOpen = rows*cols - minesUsed
		msgUpdate
	}

	def open (cell:MineCell)
	{
		def openRec (cell:MineCell)
		{
			if (cell.opened || cell.isFlagged) return
			if (cell.isMine)
			{
				cell.setText("!!"); cell.setBg(Color.red)
				loseGame; return
			}

			cell.reveal; toOpen -= 1
			if (cell.nbs == 0) cell.neighbours.map(openRec(_))
		}

		if (cell.opened && cell.neighbours.count(_.isFlagged)==cell.nbs)
			cell.neighbours.map(openRec(_))
		else
			openRec(cell)
		if (toOpen == 0) winGame
	}

	def toggleFlag (cell:MineCell) : Unit =
	{
		if (cell.opened)
		{
			val closed = cell.neighbours.filter(!_.opened)
			if (closed.length == cell.nbs)
				closed.filter(!_.isFlagged).map(toggleFlag(_))
			return
		}
		if (!cell.isFlagged && minesLeft == 0) return
		cell.isFlagged = !cell.isFlagged
		minesLeft += (if (cell.isFlagged) -1 else 1)
		cell.setText(if (cell.isFlagged) "*" else "")
		msgUpdate
	}

	reactions +=
	{
		case leftClicked (c:MineCell) => open(c)
		case rightClicked (c:MineCell) => toggleFlag(c)
	}

	defaultParams(Action("Easy") { rows = 9; cols = 9; mines = 10 } )
	addParams(Action("Medium") { rows = 16; cols = 16; mines = 40 } )
	addParams(Action("Hard") { rows = 16; cols = 16; mines = 99 } )
	addParams(Action("Custom...") { new CustomDialog(List(
		new Parameter("Rows",rows,rows = _, minimum = 1),
		new Parameter("Columns",cols,cols = _, minimum = 1),
		new Parameter("Mines",mines,mines = _)
	))})
}

object Rooks extends Game
{
	var name = "Rooks"
	var preset = 0

	var cells : Grid[RookCell] = null

	var normalBorder = createLineBorder(Color.black,2)
	var innerpreBorder = createLineBorder(Color.gray,2)
	var warnBorder = createLineBorder(Color.red,2)
	val presetBorder = createCompoundBorder(normalBorder,innerpreBorder)
	val prewarnBorder = createCompoundBorder(warnBorder,innerpreBorder)

	class RookCell (i:Int,j:Int) extends Cell(i,j)
	{
		var isBlack = false
		var isRook = false
		var isPreset = false
		var hasWarning = false

		def defStates
		{
			initial.bgColor =
				(if (isPreset) Color.black else Color.white)
			terminal.bgColor =
				(if (isRook) Color.black else Color.white)
			if (isPreset) { initial.border = presetBorder;
					terminal.border = presetBorder; }
		}

		def updateBorder =
		{
			hasWarning = isBlack &&
				(cells.row(i).count(_.isBlack) > 1
				 || cells.column(j).count(_.isBlack) > 1)
			if (hasWarning) border =
				(if (isPreset) prewarnBorder else warnBorder)
			else border =
				(if (isPreset) presetBorder else initial.border)
		}

		override def reset { super.reset; isBlack = isPreset }
	}

	def generate : Unit =
	{
		cols = rows
		preset = min(preset,rows-1)
		cells = new Grid[RookCell](rows,cols,
				(i,j) => new RookCell(i,j))
		var freeRows = (0 until rows).toBuffer
		var freeCols = (0 until rows).toBuffer
		for (i <- 1 to rows)
		{
			val c = cells(draw(freeRows),draw(freeCols))
			c.isRook = true
			if (i <= preset) c.isPreset = true
		}
		
		cells.map(_.defStates)
	}

	def reset : Unit = { messages.update("Place "+rows+" rooks") }

	def toggle (cell:RookCell) : Unit =
	{
		if (cell.isPreset) return
		cell.isBlack = !cell.isBlack
		cell.setBg(if (cell.isBlack) Color.black else Color.white)
		cells.row(cell.i).map(_.updateBorder)
		cells.column(cell.j).map(_.updateBorder)
		if (cells.count(_.hasWarning) == 0
			&& cells.count(_.isBlack) == rows) winGame
	}

	reactions += { case leftClicked (c:RookCell) => toggle(c) }

	defaultParams(Action("8x8") { rows = 8; preset = 4 } )
	addParams(Action("Custom...") { new CustomDialog(List(
		new Parameter("Rows",rows,rows = _, minimum = 1),
		new Parameter("Pre-set rooks",preset,preset = _)
	))})
}

object Welcome extends Game
{
	var name = "Welcome"
	var cells:Grid[Cell] = null

	val welcomeMsg = new Label
	{
		text = "Choose a new game";
		font = new Font("default",0,20);
		preferredSize = new Dimension(400,300)
	}

	def generate : Unit = { }
	def reset : Unit = { }
	override def newGame = new BorderPanel { layout(welcomeMsg) = Center }
	override def solve = { }
}

object main extends SimpleSwingApplication {

	val games = Array(Minesweeper,Rooks)
	var seedVal = 0
	var currentGame : Game = Welcome

	def newGame () : Unit = { top.contents = currentGame.newGame }
	def restartGame () = currentGame.restart
	def solveGame () =  currentGame.solve

	def seedGame () : Unit =
	{
		val s = Dialog.showInput(null, "New Seed:", "Random Seed",
				Dialog.Message.Plain,initial = seedVal.toString)
		try { seedVal = s.get.toInt; Random.setSeed(seedVal); newGame }
		catch { case ex: Exception => { } }
	}

	def leave () = sys.exit(0)

	def switchGame (game:Game) : Unit =
	{
		currentGame = game
		top.menuBar = remakeMenu
		newGame
	}

	class menuItem (t:String, f:()=>Unit) extends MenuItem(Action(t){f()})

	def remakeMenu () = new MenuBar
	{
		contents += new Menu("Game")
		{
			contents += new menuItem("New",newGame)
			contents += new menuItem("Restart",restartGame)
			contents += new menuItem("Solve",solveGame)
			contents += new menuItem("Seed...",seedGame)
			contents += new menuItem("Exit",leave)
		}
		contents += new Menu("Type")
		{
			games.map(g => contents +=
				new MenuItem(Action(g.name) {switchGame(g)} ))
		}
		contents += currentGame.menu
	}

	val top = new MainFrame
	{
		title = "Games"
		contents = currentGame.newGame
		menuBar = remakeMenu
		centerOnScreen()
	}
}
