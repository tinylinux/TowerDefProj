/** TowerDefProj
  * zone_VA.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._
import java.awt.Graphics2D


object ZoneVA
    extends Panel {

  override def paintComponent(
    g: Graphics2D
  ) =
    CZoneVA.paintComp(g)

}
