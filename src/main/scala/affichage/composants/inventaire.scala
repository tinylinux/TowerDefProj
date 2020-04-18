/** TowerDefProj
  * inventaire.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._
import java.awt.Point


object Inventaire
    extends Selectionneur {

  /* ATTRIBUTS */

  var tailleCase: Int = DimJeu.wInventaire/2
  var caseSel: Option[(Int, Int)] = None
  var maxX: Int = 0
  var maxY: Int = 0
  var offset: (Int, Int) = (0,0)
  var sourisSurSel: Boolean = true
  var posSouris: Point = new Point(0,0)

  var offsetMax = 0
  var dOffset = 10 // nb de pixels d'offset par evt de molette

  /* EVENEMENTS */

  listenTo(mouse.wheel)

  reactions += { CInventaire.react }

  override def paintComponent(
    g: Graphics2D
  ) =
    CInventaire.paintComp(g)
}
