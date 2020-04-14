/** TowerDefProj
  * c_zone_grille_dnd.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._

import scala.swing
import scala.swing.event._
import scala.swing.Reactions._



object CZoneGrilleDND {

  def react: Reaction = {
    case MouseWheelMoved(_, _, _, d) => ()
  }

}
