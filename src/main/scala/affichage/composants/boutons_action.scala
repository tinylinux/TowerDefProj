/** TowerDefProj
  * boutons_action.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._


abstract class BAction extends Button

object BAttaque
    extends BAction {
  text = "ATTAQUE"
}

object BAcheter
    extends BAction {
  text = "ACHETER"
}

object BDetruire
    extends BAction {
  text = "DETRUIRE"
}

