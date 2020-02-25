
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




class GrilleDeJeu(val carte: Carte) extends Panel {

  /** Charge l'image à l'emplacement src/main/resources/<name>*/
  def chargerImage(name: String): BufferedImage = {
    val fichier = new File("../resources/" + name)
    ImageIO.read(fichier)
  }

  /** Dessine le fond, les tours et les ennemis sur la carte*/
  override def paintComponent(g: Graphics2D) {
    super.paintComponent(g)

    // Dessine le fond
    g.drawImage(genererFond, null, 0, 0)
  }



  /** Renvoie une image représentant au fond de la carte*/
  def genererFond: BufferedImage = {
    // taille d'une case
    val tailleCase: Int =
      min(this.size.width / carte.maxX, this.size.height / carte.maxY)

    // image représentant le fond de la carte
    val imageCarte = new BufferedImage(
      tailleCase*carte.maxX, // width
      tailleCase*carte.maxY, // height
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
          null, x, y)
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
  }
}




