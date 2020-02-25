

/**
  PROJET PROG 2
  main.scala
*/

import Math._
import scala.util.Random
import scala.collection.mutable.Buffer
import scala.swing._
import scala.swing.BorderPanel.Position._
import javax.swing.BorderFactory._
import javax.swing.border.Border
import java.awt.{ Color, Font }
import event._

/*
  À implémenter :
  - classe abstraite Game
  - classe Welcome qui hérite de Game
*/

abstract class Game extends Reactor
{
  def name : String
  def generate : Unit
  def reset : Unit

  val menu = new Menu("Paramètres")

  def addParams (a:Action) = {
    menu.contents += new MenuItem(Action(a.title)) {a.apply(); main.newGame()}
  }

  def defaultParams (a:Action) = {
    a.apply();
    addParams(a)
  }

  val messages = new Label {
    preferredSize = new Dimension(0, 25)
    font = new Font("default", 0, 18)
    def update (s: String) = {text = s}
    def empty = {text = ""}
  }

  def loseGame = {messages.update("Perdu"); stopGame(false)}
  def winGame = {messages.update("Gagné"); stopGame(true)}

  def newGame () : BorderPanel = {
    // à compléter
  }

}

object Welcome extends Game {
  var name = "Bienvenue"

  val welcomeMsg = new Label
}

object GameInterface extends SimpleSwingApplication {
  val games = Array(TowerDef)
  val pseudo = ""
  var currentGame : Game = Welcome

  def newGame () : Unit = {top.contents = currentGame.newGame}
  def restartGame () = currentGame.restart

  def pseudoGame () = Unit =
    {
      val s = Dialog.showInput(null, "Pseudo :", "Pseudonyme du joueur",
              Dialog.Message.Plain, initial = pseudo)
      try {
        pseudo = s.get.toInt;
        newGame
      } catch {
        case ex: Exception => {}
      }
    }

  def leave () = sys.exit(0)

  def switchGame(game : Game) : Unit =
    {
      currentGame = game
      top.menuBar = remakeMenu
      newGame
    }

  class menuItem (t: String, f:()=>Unit) extends MenuItem(Action(t){f()})

  def remakeMenu () = new MenuBar
  {
    contents += new Menu("Jeu")
    {
      contents += new menuItem("Nouveau", newGame)
      contents += new menuItem("Rejouer", restart)
      contents += new menuItem("Quitter", leave)
    }
    contents += new Menu("Paramètres")
    {
      contents += new menuItem("Pseudo", pseudoGame)
    }
    contents += currentGame.menu
  }

  val top = new MainFrame
  {
    title = "TowerDef"
    preferredSize = new Dimension(1000,700)
    contents = currentGame.newGame
    menuBar = remakeMenu
    centerOnScreen()
  }
}

/**
  def main(args: Array[String]) {
    val ui = new UI
    ui.visible = true
    println("End of main function")
  }
  */
