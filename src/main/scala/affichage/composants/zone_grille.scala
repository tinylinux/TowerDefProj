/** TowerDefProj
  * zone_grille.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._


object ZoneGrille
    extends Selectionneur {

  /* ATTRIBUTS */

  var tailleCase: Int = DimJeu.tabTuileZoneGrille(CZoom.i)
  var caseSel: Option[(Int, Int)] = None
  var maxX: Int = 0
  var maxY: Int = 0
  var offset: (Int, Int) = (0,0)
  var sourisSurSel: Boolean = true
  var posSouris: Point = new Point(0,0)

  /* EVENEMENTS */

  listenTo(mouse.wheel)

  reactions += { CZoneGrilleDND.react }
  reactions += { CZoomCtrl.react }

  override def paintComponent(
    g: Graphics2D
  ) =
    CZoneGrilleA.paintComp(g)

}
