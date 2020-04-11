/** TowerDefProj
  * zone_VA.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._


object ZoneVA
    extends Panel {

  MenuBas.peer.add(this.peer)
  this.peer.setBounds(0,0,DimJeu.wZoneVA,DimJeu.hZoneVA)

}
