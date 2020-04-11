/** TowerDefProj
  * fenetre.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu

import scala.swing._


object FenetreJeu
    extends MainFrame {

  title = "TowerDefProj"

  contents = new Panel {
    peer.setLayout(null)
  }

  resizable = false
  size = new Dimension(wFenetre,hFenetre+25)

}
