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
    BPause.enable = false
    BSlow.enable = false
    BFast.enable = false
    BParam.enable = false

    /* Boutons Actions */
    BAttaque.enable = false
    BAcheter.enable = false
    BDetruire.enable = false

    /* TimerJeu */
    TimerJeu.stop()
  }


  def mancheOn = {
    /* Boutons Jeu */
    BPause.enable = true
    BSlow.enable = true
    BFast.enable = true
    BParam.enable = true

    /* Boutons Actions */
    BAttaque.enable = false
    BAcheter.enable = false
    BDetruire.enable = false

    /* TimerJeu */
    TimerJeu.stop()
  }


  def mancheOff = {
    /* Boutons Jeu */
    BPause.enable = true
    BSlow.enable = true
    BFast.enable = true
    BParam.enable = true

    /* Boutons Actions */
    BAttaque.enable = true
    BAcheter.enable = true
    BDetruire.enable = true

    /* TimerJeu */
    TimerJeu.start()
  }


  def paintComp(
    g: Graphics2D
  ) = 
    MainFrame.paintComponent(g)
}
