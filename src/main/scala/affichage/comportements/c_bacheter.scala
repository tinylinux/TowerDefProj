/** TowerDefProj
  * c_bacheter.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._



object CBAcheter {

  def clic: Unit = {
    if (CPartAff.partie.isDefined) {
      Inventaire.caseSel match {
        case Some(a) => {
          ZoneGrille.caseSel match {
            case Some(b) => {
              /* partie chargée, contrat et tuile sélectionnées */
              CPartAff.partie.get.acheter(a._2*2 + a._1, b)
            }
          }
        }
      }
    }
  }


}
