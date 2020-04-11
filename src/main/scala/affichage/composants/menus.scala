/** TowerDefProj
  * menus.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._


object MenuBas
    extends Panel {
  FenetreJeu.peer.add(this.peer)
  this.peer.setBounds(DimJeu.wMenuGauche,DimJeu.hMenuGrille,DimJeu.wMenuBas,DimJeu.hMenuBas)
}

object MenuGauche
    extends Panel {
  FenetreJeu.peer.add(this.peer)
  this.peer.setBounds(0,0,DimJeu.wMenuGauche,DimJeu.hMenuGauche)
}

object MenuGrille
    extends Panel {
  FenetreJeu.peer.add(this.peer)
  this.peer.setBounds(DimJeu.wMenuGauche,0,DimJeu.wMenuGrille,DimJeu.hMenuGrille)
}


object ZoneBoutons
    extends Panel {
  MenuGauche.peer.add(this.peer)
  this.peer.setBounds(0,0,DimJeu.wZoneBoutons,DimJeu.hZoneBoutons)
}

object ZoneActions
    extends Panel {
  MenuGauche.peer.add(this.peer)
  this.peer.setBounds(0,DimJeu.hZoneBoutons,DimJeu.wZoneActions,DimJeu.hZoneActions)
}
