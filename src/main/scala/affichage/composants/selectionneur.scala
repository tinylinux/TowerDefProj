/** TowerDefProj
  * selectionneur.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._
import java.awt.Point
import java.awt.Graphics2D


abstract class Selectionneur extends Panel {
  this.peer.setLayout(null)

  /* ATTRIBUTS */

  var sourisSurSel: Boolean // si la souris est sur le selectionneur
  var posSouris: Point // position de la souris sur le selectionneur
  var tailleCase: Int
  var caseSel: Option[(Int, Int)]
  var maxX: Int
  var maxY: Int
  var offset: (Int, Int)


  /* EVENEMENTS */

  listenTo(mouse.clicks)
  listenTo(mouse.moves)

  reactions += { CSelectionneur.react(this) }

  override def paintComponent(
    g: Graphics2D
  ) =
    CSelectionneur.paintComp(this, g)
}
