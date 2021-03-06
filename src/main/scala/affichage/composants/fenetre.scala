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

  /* ATTRIBUTS */

  var manche = false
  /* Une manche est chargée, les boutons d'action sont bloqués */
  var bloque = false
  /* Tous les boutons, et TimerJeu sont bloqués lors de l'affichage
   * d'un message en plein écran */
  var pause = false
  /* Le jeu est en pause */

  title = "TowerDefProj"

  Placement.placerComposants
  
  resizable = false
  size = new Dimension(DimJeu.wFenetreJeu,DimJeu.hFenetreJeu+25)

  peer.setVisible(true)

  /* METHODES */

  override def closeOperation() =
    CFinJeu.closeOp

}
