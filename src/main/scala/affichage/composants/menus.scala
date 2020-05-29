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
    extends Panel

object MenuGauche
    extends Panel

object MenuGrille
    extends Panel {
  listenTo(BMoins)
  listenTo(BPlus)
  listenTo(BCentre)

  reactions += {
    case ButtonClicked(BMoins) => CZoom.moins
    case ButtonClicked(BPlus) => CZoom.plus
    case ButtonClicked(BCentre) => CZoom.centrer
  }
}



object ZoneBoutons
    extends Panel {
  listenTo(BCharger)
  listenTo(BSlow)
  listenTo(BFast)
  listenTo(BSauvegarder)

  reactions += {
    case ButtonClicked(BCharger) => CBCharger.clic
    case ButtonClicked(BSlow) => CFreqTimer.freqSlow
    case ButtonClicked(BFast) => CFreqTimer.freqFast
    case ButtonClicked(BSauvegarder) => CBSauvegarder.clic
  }
}

object ZoneActions
    extends Panel {
  listenTo(BAttaque)
  listenTo(BAcheter)
  listenTo(BDetruire)

  reactions += {
    case ButtonClicked(BAttaque) => CBAttaque.clic
    case ButtonClicked(BAcheter) => CBAcheter.clic
    case ButtonClicked(BDetruire) => CBDetruire.clic
  }
}
