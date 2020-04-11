/** TowerDefProj
  * inventaire.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._


object Inventaire
    extends Selectionneur {

  MenuGauche.peer.add(this.peer)
  this.peer.setBounds(0,DimJeu.hZoneBoutons+DimJeu.hZoneActions,DimJeu.wInventaire,DimJeu.hInventaire)

}
