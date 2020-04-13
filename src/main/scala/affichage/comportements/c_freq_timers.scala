/** TowerDefProj
  * timers.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._

import scala.swing._
import javax.swing.Timer
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import scala.Array


object CFreqTimer {
  /* FPS (en Hz) */
  var tabFpsJeu = Array(1,2,4,8,16,32,64,128,256)
  var iJeu = 3
  var fpsAff = 30

  /* DELAIS (en ms) */
  def d(f: Int) = 1000/f

  /* CHANGEMENT DE LA VITESSE DU JEU */
  def freqFast = {
    if (iJeu < tabFpsJeu.length - 1) {
      iJeu += 1
      TimerJeu.setDelay(d(tabFpsJeu(iJeu)))
    }
  }

  def freqSlow = {
    if (iJeu > 0) {
      iJeu -= 1
      TimerJeu.setDelay(d(tabFpsJeu(iJeu)))
    }
  }
}
