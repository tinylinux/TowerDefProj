/** TowerDefProj
  * c_zoom.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._



object CZoom {

  var i = DimJeu.iTuileZoneGrilleInit

  def tailleCase = DimJeu.tabTuileZoneGrille(i)

  def plus = {
    if (i < DimJeu.tabTuileZoneGrille.length-1) {
      i += 1
      ZoneGrille.tailleCase = tailleCase
    }
  }

  def moins = {
    if (i > 0) {
      i -= 1
      ZoneGrille.tailleCase = tailleCase
    }
  }

}

