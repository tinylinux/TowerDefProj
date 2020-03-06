
/** PROJET PROG 2
  * affichage.scala
  */

package affichage

import jeu._

import scala.swing.event._
import scala.swing._
import scala.math._
import java.awt.image.BufferedImage
import java.awt.Image
import java.awt.Color
import java.io._
import javax.imageio.ImageIO
import javax.swing.Timer
import java.awt.event._



trait HasSprite {
  def sprite : BufferedImage
}


object MethodesAffichage {

  /** Charge l'image à l'emplacement src/main/resources/<name>*/
  def chargerImage(name: String): BufferedImage = {
    val fichier = new File("src/main/resources/" + name)
    ImageIO.read(fichier)
  }

}


object ApplicationJeu extends SimpleSwingApplication {

  val carte = new CarteTest

  def top = new FenetreDeJeu(carte)
}


class ImageRedimensionnee (str: String, w: Int, h: Int)
    extends BufferedImage(w, h, BufferedImage.TYPE_INT_RGB) {
  val graphics = createGraphics()

  graphics.drawImage(
    MethodesAffichage.chargerImage(str).getScaledInstance(w,h,Image.SCALE_DEFAULT),
    0,0,null)
}
