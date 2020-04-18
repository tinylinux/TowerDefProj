/** TowerDefProj
  * bouton_logo.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._
import javax.swing.ImageIcon


abstract class BLogo
    extends Button {

  var nom: String // nom du fichier
  var l: Int // largeur du bouton

  def initIcon =
    this.peer.setIcon(CImgJeu.imgBLogo(nom, l))
}
  
