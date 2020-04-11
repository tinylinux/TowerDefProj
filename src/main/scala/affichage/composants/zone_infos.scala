/** TowerDefProj
  * zone_infos.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu

import scala.swing._


object ZoneInfos
    extends Panel {

  MenuBas.peer.add(this.peer)
  this.peer.setBounds(DimJeu.wZoneVA,0,DimJeu.wZoneInfos,DimJeu.hZoneInfos)

}

