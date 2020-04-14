/** TowerDefProj
  * menus.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._
import scala.swing.event._


object MenuBas
    extends Panel {
  this.peer.setLayout(null)
  FenetreJeu.peer.add(this.peer)
  this.peer.setBounds(DimJeu.wMenuGauche,DimJeu.hMenuGrille,DimJeu.wMenuBas,DimJeu.hMenuBas)
}

object MenuGauche
    extends Panel {
  this.peer.setLayout(null)
  FenetreJeu.peer.add(this.peer)
  this.peer.setBounds(0,0,DimJeu.wMenuGauche,DimJeu.hMenuGauche)
}

object MenuGrille
    extends Panel {
  this.peer.setLayout(null)
  FenetreJeu.peer.add(this.peer)
  this.peer.setBounds(DimJeu.wMenuGauche,0,DimJeu.wMenuGrille,DimJeu.hMenuGrille)
}


object ZoneBoutons
    extends Panel {
  this.peer.setLayout(null)
  MenuGauche.peer.add(this.peer)
  this.peer.setBounds(0,0,DimJeu.wZoneBoutons,DimJeu.hZoneBoutons)

  listenTo(BPause)
  listenTo(BSlow)
  listenTo(BFast)
  listenTo(BParam)

  reactions += {
    case ButtonClicked(BPause) => CBPause.clic
    case ButtonClicked(BSlow) => CFreqTimer.freqSlow
    case ButtonClicked(BFast) => CFreqTimer.freqFast
    case ButtonClicked(BParam) => CBParam.clic
  }
}

object ZoneActions
    extends Panel {
  this.peer.setLayout(null)
  MenuGauche.peer.add(this.peer)
  this.peer.setBounds(0,DimJeu.hZoneBoutons,DimJeu.wZoneActions,DimJeu.hZoneActions)

  listenTo(BAttaque)
  listenTo(BAcheter)
  listenTo(BDetruire)

  reactions += {
    case ButtonClicked(BAttaque) => CBAttaque.clic
    case ButtonClicked(BAcheter) => CBAcheter.clic
    case ButtonClicked(BDetruire) => CBDetruire.clic
  }
}
