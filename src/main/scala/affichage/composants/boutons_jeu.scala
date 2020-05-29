/** TowerDefProj
  * boutons_jeu.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._


object BCharger
    extends BLogo {
  var nom: String = "icones/charger.jpg"
  var l: Int = DimJeu.lBJeu
  initIcon
}

object BSlow
    extends BLogo {
  var nom: String = "icones/slow.jpg"
  var l: Int = DimJeu.lBJeu
  initIcon
}

object BFast
    extends BLogo {
  var nom: String = "icones/fast.jpg"
  var l: Int = DimJeu.lBJeu
  initIcon
}

object BSauvegarder
    extends BLogo {
  var nom: String = "icones/sauvegarder.jpg"
  var l: Int = DimJeu.lBJeu
  initIcon
}
