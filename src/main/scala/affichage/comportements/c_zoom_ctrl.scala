/** TowerDefProj
  * c_zoom_ctrl.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._

import scala.swing._
import scala.swing.Reactions._
import scala.swing.event._
import java.awt.Point



object CZoomCtrl {

  def react: Reaction = {
    case MouseWheelMoved(_, p, _, d) => {
      if (d == 1) { // d==1 : vers le haut
        if (CZoom.i < DimJeu.tabTuileZoneGrille.length-1) {
          CZoom.i += 1
          CZoom.zoom(p.x, p.y)
        }
      }
      else { // d==-1 : vers le bas
        if (CZoom.i > 0) {
          CZoom.i -= 1
          CZoom.zoom(p.x, p.y)
        }
      }
    }
  }


}
