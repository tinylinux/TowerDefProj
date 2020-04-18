/** TowerDefProj
  * boutons_zoom.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._


object BMoins
    extends BLogo {
  var nom: String = "icones/moins.jpg"
  var l: Int = DimJeu.lBZoom
  initIcon
}

object BPlus
    extends BLogo {
  var nom: String = "icones/plus.jpg"
  var l: Int = DimJeu.lBZoom
  initIcon
}

object BCentre
    extends BLogo {
  var nom: String = "icones/centrer.jpg"
  var l: Int = DimJeu.lBZoom
  initIcon
}
