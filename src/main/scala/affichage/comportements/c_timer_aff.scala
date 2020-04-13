/** TowerDefProj
  * c_timer_aff.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._



object CTimerAff {

  def tick: Unit = {
    CFinJeu.actuFin
    fenetre.repaint()
  }

}
