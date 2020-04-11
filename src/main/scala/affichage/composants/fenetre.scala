/** TowerDefProj
  * fenetre.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._


object FenetreJeu
    extends MainFrame {

  title = "TowerDefProj"

  contents = new Panel {
    peer.setLayout(null)
  }

  resizable = false
  size = new Dimension(DimJeu.wFenetreJeu,DimJeu.hFenetreJeu+25)

}
