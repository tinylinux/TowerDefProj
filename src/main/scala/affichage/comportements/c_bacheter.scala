/** TowerDefProj
  * c_bacheter.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._



object CBAcheter {

  def clic: Unit = {
    if (CPartAff.partie.isDefined
      && Inventaire.caseSel.isDefined
      && ZoneGrille.caseSel.isDefined) {
      /* partie chargée, contrat et tuile sélectionnées */
      CPartAff.partie.get.acheter(
        Inventaire.caseSel.get._2*2 + Inventaire.caseSel._1,
        ZoneGrille.caseSel.get
      )
    }
  }


}
