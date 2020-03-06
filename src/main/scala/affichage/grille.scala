
/** PROJET PROG 2
  * grille.scala
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


/** Panel permettant l'affichage de la grille de jeu */
class GrilleDeJeu(val carte: Carte)
    extends Panel {

  // nombre de cases horizontalement et verticalement
  val nbX = carte.maxX + 1
  val nbY = carte.maxY + 1

  // taille d'une case (en pixels) lors de l'affichage du fond
  val tailleCase = 64

  class ImageFond extends BufferedImage (
    tailleCase*nbX, // width
    tailleCase*nbY, // height
    BufferedImage.TYPE_INT_RGB // imageType
  ) {
    val graphics = createGraphics()

    // dessiner chaque case
    var x = 0
    var y = 0
    carte.cases.foreach(l => {
      x = 0
      l.foreach(c => {
        // dessiner
        graphics.drawImage(
          c.sprite.getScaledInstance(tailleCase, tailleCase, Image.SCALE_DEFAULT),
          x, y, null)
        x += tailleCase
      } )
      y += tailleCase
    } )

  }

  // image représentant le fond de la carte
  var imageFond: BufferedImage = new ImageFond

  /** Dessine le fond, les tours et les ennemis sur la carte*/
  override def paintComponent(g: Graphics2D) {
    super.paintComponent(g)

    // Dessine le fond
    g.drawImage(imageFond, 0, 0, null)

    // Dessine les tours
    carte.tours.foreach(t =>
      t.pos match {
        case None => ()
        case Some((x,y)) =>
          g.drawImage(
            t.sprite.getScaledInstance(tailleCase, tailleCase, Image.SCALE_DEFAULT),
            x*tailleCase,
            y*tailleCase,
            null)
      } )

    class BarreDeVie(pv: Int, pvMax: Int) extends BufferedImage(
      tailleCase, // width
      tailleCase/10, // height
      BufferedImage.TYPE_INT_RGB // imageType
    ) {
      val graphics = createGraphics()

      // délimitation vert rouge
      var xlim = tailleCase*pv/pvMax

      // dessiner vert puis rouge
      graphics.setColor(Color.green)
      graphics.fill(new Rectangle(0,0,xlim,tailleCase/10-1))
      graphics.setColor(Color.red)
      graphics.fill(new Rectangle(xlim+1,0,(tailleCase-1)-(xlim+1),tailleCase/10-1))
    }


    // Dessine les ennemis
    carte.ennemis.foreach(e =>
      e.pos match {
        case None => ()
        case Some((x,y)) =>
          g.drawImage(
            e.sprite.getScaledInstance(tailleCase, tailleCase, Image.SCALE_DEFAULT),
            (x*tailleCase - tailleCase/2).toInt,
            (y*tailleCase - tailleCase/2).toInt,
            null)
          g.drawImage(
            new BarreDeVie(e.pv, e.pvMax),
            (x*tailleCase - tailleCase/2).toInt,
            (y*(tailleCase+1) - tailleCase/2).toInt,
            null)
      } )

  }


  /** Met à jour l'image représentant le fond de la carte */
  def updateFond: BufferedImage = {
    imageFond = new ImageFond
    imageFond
  }

}
