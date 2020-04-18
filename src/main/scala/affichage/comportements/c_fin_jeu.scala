/** TowerDefProj
  * c_fin_jeu.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._



object CFinJeu {

  def closeOp: Unit = {
    TimerJeu.stop()
    TimerAff.stop()

    System.exit(0)
  }


  def actuFin: Unit = {
    if ((!CPartAff.partie.isDefined) || CPartAff.partie.get.gagne.isDefined) { // aucune partie chargée, ou partie chargée et terminée
      if (!FenetreJeu.bloque) {
        CFenetreJeu.bloquer
        FenetreJeu.bloque = true
      } }
    else { // une partie est chargée et non terminée
      FenetreJeu.bloque = false
      if (!CPartAff.partie.get.gM.mEnCours.isDefined) { // aucune manche n'est chargée
        if (FenetreJeu.manche) {
          CFenetreJeu.mancheOff
          FenetreJeu.manche = false
        }
      }
      else { // une manche est chargée (en cours)
        if (!FenetreJeu.manche) {
          CFenetreJeu.mancheOn
          FenetreJeu.manche = true
        }
      }
    }
  }



}
