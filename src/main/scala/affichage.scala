
/** PROJET PROG 2
  * affichage.scala
  */



import swing._
import scala.math._
import java.awt.image.BufferedImage
import java.awt.Image
import java.io._
import javax.imageio.ImageIO


trait HasSprite {
  def sprite : BufferedImage
  // à modifier, une fois que le type d'une image sera défini

}

object MethodesAffichage {

  /** Charge l'image à l'emplacement src/main/resources/<name>*/
  def chargerImage(name: String): BufferedImage = {
    val fichier = new File("src/main/resources/" + name)
    ImageIO.read(fichier)
  }

}



class GrilleDeJeu(val carte: Carte) extends Panel {


  // nombre de cases horizontalement et verticalement
  val nbX = carte.maxX + 1
  val nbY = carte.maxY + 1



  // taille d'une case
  def genererTailleCase: Int =
    min(this.size.width / nbX, this.size.height / nbY)
  var tailleCase: Int = genererTailleCase


  /** Dessine le fond, les tours et les ennemis sur la carte*/
  override def paintComponent(g: Graphics2D) {
    super.paintComponent(g)

    tailleCase = genererTailleCase

    // Dessine le fond
    g.drawImage(genererFond, 0, 0, null)

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
      } )

  }



  /** Renvoie une image représentant au fond de la carte*/
  def genererFond: BufferedImage = {

    // image représentant le fond de la carte
    val imageCarte = new BufferedImage(
      tailleCase*nbX, // width
      tailleCase*nbY, // height
      BufferedImage.TYPE_INT_RGB // imageType
    )

    // pour dessiner sur l'image
    val graphics = imageCarte.createGraphics()

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

    imageCarte
  }

}



object FenetreDeJeu extends SimpleSwingApplication {

  val carte = new CarteTest

  def top = new MainFrame {
    title = "TowerDefProj"


    contents = new GrilleDeJeu(carte)

    size = new Dimension(800, 480)

  }
}




