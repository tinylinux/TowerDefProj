/** TowerDefProj
  * c_fenetre.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._

import scala.swing._
import java.awt.Graphics2D


object CFenetreJeu {



  /* bloquer, debloquer, mancheOn et mancheOff effectuent l'activation/désactivation du timer et des boutons dans les différentes situations */
  def bloquer: Unit = {
    /* Boutons Jeu */
    BPause.enabled = false
    BSlow.enabled = false
    BFast.enabled = false
    BParam.enabled = false

    /* Boutons Actions */
    BAttaque.enabled = false
    BAcheter.enabled = false
    BDetruire.enabled = false

    /* TimerJeu */
    TimerJeu.stop()
  }


  def mancheOn = {
    /* Boutons Jeu */
    BPause.enabled = true
    BSlow.enabled = true
    BFast.enabled = true
    BParam.enabled = true

    /* Boutons Actions */
    BAttaque.enabled = false
    BAcheter.enabled = false
    BDetruire.enabled = false

    /* TimerJeu */
    TimerJeu.stop()
  }


  def mancheOff = {
    /* Boutons Jeu */
    BPause.enabled = true
    BSlow.enabled = true
    BFast.enabled = true
    BParam.enabled = true

    /* Boutons Actions */
    BAttaque.enabled = true
    BAcheter.enabled = true
    BDetruire.enabled = true

    /* TimerJeu */
    TimerJeu.start()
  }


  def paintComp(
    g: Graphics2D
  ) = ()
}
