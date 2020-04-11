/** TowerDefProj
  * inventaire.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu

import scala.swing._


object Inventaire
    extends Selectionneur {

  MenuGauche.peer.add(this.peer)
  this.peer.setBounds(0,hZoneBoutons+hZoneActions,wInventaire,hInventaire)

}
