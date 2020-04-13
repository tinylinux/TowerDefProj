/** TowerDefProj
  * c_bdetruire.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._



object CBDetruire {

  def clic: Unit = {
    if (CPartAff.partie.isDefined
      && ZoneGrille.caseSel.isDefined) {
      // partie chargée et tuile sélectionnée
      CPartAff.partie.get.carte.getTourAt(ZoneGrille.caseSel.get) match {
        case None => ()
        case Some(t: Tour) => OEndommageable.mourir(t)
      }
    }
  }

  

}
