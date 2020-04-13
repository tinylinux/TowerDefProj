/** TowerDefProj
  * c_timer_jeu.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._



object CTimerJeu {

  def tick: Unit = {
    if (CPartAff.partie.isDefined
      && (!CPartAff.partie.get.gagne.isDefined)
      && CPartAff.partie.get.gM.mEnCours.isDefined) {
      CPartAff.partie.get.tick
    }
    else {
      TimerJeu.stop()
    }
  }

  
}
