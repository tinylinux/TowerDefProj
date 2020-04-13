/** TowerDefProj
  * c_img_jeu.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._

import java.awt.image.BufferedImage
import javax.swing.ImageIcon


object CImgJeu {

  def imgTypeEndom(
    t: TypeEndommageable,
    res: (Int, Int)
  ): BufferedImage = null

  def imgTuile(
    t: Tuile,
    res: (Int, Int)
  ): BufferedImage = null

  def img(
    nom: String,
    dim: (Int, Int)
  ) = null

  def imgBLogo(
    nom: String,
    l: Int
  ): ImageIcon = null
}
