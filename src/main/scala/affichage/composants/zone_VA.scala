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
  this.peer.setLayout(null)
  MenuBas.peer.add(this.peer)
  this.peer.setBounds(0,0,DimJeu.wZoneVA,DimJeu.hZoneVA)

  override def paintComponent(
    g: Graphics2D
  ) =
    CZoneVA.paintComp(g)
}
