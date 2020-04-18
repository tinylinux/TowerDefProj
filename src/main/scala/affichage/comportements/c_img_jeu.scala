/** TowerDefProj
  * c_img_jeu.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._

import scala.collection.immutable.HashMap
import java.io.File
import javax.imageio.ImageIO
import java.awt.Image
import java.awt.image.BufferedImage
import javax.swing.ImageIcon


object CImgJeu {

  var imgChargees = new HashMap[String, BufferedImage]()
  var imgRedim = new HashMap[(String, (Int, Int)), BufferedImage]()
  var imgRedimLogo = new HashMap[(String, Int), ImageIcon]()

  def imgTypeEndom(
    t: TypeEndommageable,
    res: (Int, Int)
  ): BufferedImage = img(t.img, res)

  def imgTypeEffet(
    t: TypeEffet,
    res: (Int, Int)
  ): BufferedImage = img(t.img, res)

  def imgTuile(
    t: Tuile,
    res: (Int, Int)
  ): BufferedImage = img(t.img, res)


  /* Charger une nouvelle image (ou pas, si elle est déjà chargée) */
  def charger(
    nom: String
  ): Unit = {
    if (!imgChargees.contains(nom)) {
      // l'image n'est pas encore chargée
      val fichier = new File("src/main/resources/images/" + nom)
      try {
        val image = ImageIO.read(fichier)
        val kv = (nom, image)
        imgChargees = imgChargees + kv
      } catch {
        case _ : Throwable => println("Erreur chargement image \"" + nom + "\"")
      }
    }
  }


  /* Redimensionner une image chargée (ou pas, si elle est déjà dimensionnée ou pas chargée) */
  def redim(
    nom: String,
    res: (Int, Int)
  ): Unit = {
    if (!imgRedim.contains((nom, res)) && imgChargees.contains(nom)) {
      // image chargée et pas redimensionnée
      val image = new BufferedImage(res._1, res._2, BufferedImage.TYPE_INT_RGB)
      val g = image.createGraphics()
      g.drawImage(
        imgChargees(nom).getScaledInstance(res._1, res._2, Image.SCALE_DEFAULT),
        0, 0, null)
      g.dispose()
      val kv = ((nom, res), image)
      imgRedim = imgRedim + kv
    }
  }


  /* Convertir une image redimensionnée dans imgRedim en ImageIcon dans imgRedimLogo (ou pas, si l'ImageIcon existe déjà ou si l'image n'existe pas dans imgRedim) */
  def redimLogo(
    nom: String,
    l: Int
  ): Unit = {
    if (!imgRedimLogo.contains((nom, l)) && imgRedim.contains((nom, (l,l)))) {
      // image redimensionnée dans imgRedim et pas redimensionnée dans imgRedimLogo
      val kv = ((nom, l), new ImageIcon(imgRedim((nom, (l,l)))))
      imgRedimLogo = imgRedimLogo + kv
    }
  }


  /* Renvoyer une image avec la bonne dimension */
  def img(
    nom: String,
    res: (Int, Int)
  ): BufferedImage = {
    charger(nom)
    redim(nom, res)
    imgRedim((nom, res))
  }



  /* Renvoyer une ImageIcon avec la bonne dimension (carré de l pixels) */
  def imgBLogo(
    nom: String,
    l: Int
  ): ImageIcon = {
    charger(nom)
    redim(nom, (l,l))
    redimLogo(nom, l)
    imgRedimLogo((nom, l))
  }


}
