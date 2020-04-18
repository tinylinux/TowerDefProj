/** TowerDefProj
  * zone_infos.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._
import java.awt.Graphics2D


object ZoneInfos
    extends Panel {

  var c: Option[Contrat] = None
  var t: Option[Tour] = None

  override def paintComponent(
    g: Graphics2D
  ) = CZoneInfos.paintComp(g)

}

