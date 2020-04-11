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
  ZoneActions.peer.add(this.peer)
  this.peer.setBounds(0,0,DimJeu.wBAction,DimJeu.hBAction)
}

object BAcheter
    extends BAction {
  ZoneActions.peer.add(this.peer)
  this.peer.setBounds(0,DimJeu.hBAction,DimJeu.wBAction,DimJeu.hBAction)
}

object BDetruire
    extends BAction {
  ZoneActions.peer.add(this.peer)
  this.peer.setBounds(0,DimJeu.hBAction*2,DimJeu.wBAction,DimJeu.hBAction)
}

