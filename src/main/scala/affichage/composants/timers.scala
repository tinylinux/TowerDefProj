/** TowerDefProj
  * timers.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._
import javax.swing.Timer
import java.awt.event.ActionListener
import java.awt.event.ActionEvent



object TimerJeu
    extends Timer(CFreqTimer.d(CFreqTimer.tabFpsJeu(CFreqTimer.iJeu)),
      new ActionListener {
        override def actionPerformed(
          e: ActionEvent) =
          CTimerJeu.tick
      } ) {
  this.stop()
}

  
object TimerAff
    extends Timer(CFreqTimer.d(CFreqTimer.fpsAff),
      new ActionListener {
        override def actionPerformed(
          e: ActionEvent) =
          CTimerAff.tick
      } )
