/** TowerDefProj
  * c_battaque.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._



object CBAttaque {

  def clic: Unit = {
    if (!FenetreJeu.manche && CPartAff.partie.isDefined) {
      // une partie est chargée, pas de manche en cours dans la fenetre
      CPartAff.partie.get.gM.chargerM
      CFinJeu.actuFin
    }
  }

}
