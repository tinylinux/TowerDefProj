/** TowerDefProj
  * zone_grille.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._


object ZoneGrille
    extends Panel {

  MenuGrille.peer.add(this.peer)
  this.peer.setBounds(0,0,DimJeu.wZoneGrille,DimJeu.hZoneGrille)

}
