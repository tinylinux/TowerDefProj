
/** PROJET PROG 2
  * zonevie.scala
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
import java.awt.event.ActionListener
import java.awt.Font



object ZoneVie {

  val tailleImage = 40

  val imageCoeur = new ImageRedimensionnee("coeur.png", tailleImage, tailleImage)
  val imageDollar = new ImageRedimensionnee("dollar.jpg", tailleImage, tailleImage)
}


class ZoneVie(val carte: Carte) extends Panel {

  override def paintComponent(g: Graphics2D) {
    super.paintComponent(g)

    // dessine les images
    g.drawImage(ZoneVie.imageCoeur, 5, 5, null)
    g.drawImage(ZoneVie.imageDollar, 5, 55, null)

    // écrit le nb de vie et d'argent
/*
    val pv = carte.tourPrincipale.pv.toString
    val pvMax = carte.tourPrincipale.pvMax.toString
 */
    val pv = carte.tourPrincipale.pv
    val pvMax = carte.tourPrincipale.pvMax
    val argent = carte.argent.toString

    g.setFont(new Font("Impact", Font.BOLD, 24))

    g.drawString(pv + "/" + pvMax, 50, 40)
    g.drawString(argent, 50, 80)
  }
}
