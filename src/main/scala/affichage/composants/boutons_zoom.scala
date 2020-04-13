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
  nom = "moins.png"
  l = DimJeu.lBZoom

  MenuGrille.peer.add(this.peer)
  this.peer.setBounds(
    DimJeu.wMenuGrille - 3*DimJeu.lBZoom - 3*DimJeu.dlBZoom,
    DimJeu.dlBZoom,
    DimJeu.lBZoom, DimJeu.lBZoom)
}

object BPlus
    extends BLogo {
  nom = "plus.png"
  l = DimJeu.lBZoom

  MenuGrille.peer.add(this.peer)
  this.peer.setBounds(
    DimJeu.wMenuGrille - 2*DimJeu.lBZoom - 2*DimJeu.dlBZoom,
    DimJeu.dlBZoom,
    DimJeu.lBZoom, DimJeu.lBZoom)
}

object BCentre
    extends BLogo {
  nom = "centre.png"
  l = DimJeu.lBZoom

  MenuGrille.peer.add(this.peer)
  this.peer.setBounds(
    DimJeu.wMenuGrille - DimJeu.lBZoom - DimJeu.dlBZoom,
    DimJeu.dlBZoom,
    DimJeu.lBZoom, DimJeu.lBZoom)
}
