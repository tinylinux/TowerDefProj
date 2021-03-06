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
    BCharger.enabled = false
    BSlow.enabled = false
    BFast.enabled = false
    BSauvegarder.enabled = false

    /* Boutons Actions */
    BAttaque.enabled = false
    BAcheter.enabled = false
    BDetruire.enabled = false

    /* TimerJeu */
    TimerJeu.stop()
  }


  def mancheOn = {
println("MANCHE ON")
    /* Boutons Jeu */
    BCharger.enabled = true
    BSlow.enabled = true
    BFast.enabled = true
    BSauvegarder.enabled = true

    /* Boutons Actions */
    BAttaque.enabled = false
    BAcheter.enabled = false
    BDetruire.enabled = false

    /* TimerJeu */
    TimerJeu.start()
  }


  def mancheOff = {
println("MANCHE OFF")  
    /* Boutons Jeu */
    BCharger.enabled = true
    BSlow.enabled = true
    BFast.enabled = true
    BSauvegarder.enabled = true

    /* Boutons Actions */
    BAttaque.enabled = true
    BAcheter.enabled = true
    BDetruire.enabled = true

    /* TimerJeu */
    TimerJeu.stop()
  }


  def paintComp(
    g: Graphics2D
  ) = ()
}
