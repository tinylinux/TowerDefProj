
/** PROJET PROG 2
  * inventaire.scala
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
import java.awt.BasicStroke



abstract class TourInvent extends HasSprite {
  val resizedSprite: BufferedImage
  override def sprite = resizedSprite

  def creerInstance: Tour
}


class InventairePanel(var listeItems: List[TourInvent]) extends Panel {

  var caseSelect: Option[(Int,Int)] = None

  val tailleCase = 50
  val eltParLigne = 2


  override def paintComponent(g: Graphics2D) {
    super.paintComponent(g)

    // Dessine la grille
    var i = 0
    listeItems.foreach(t =>
      g.drawImage(
        t.sprite.getScaledInstance(tailleCase, tailleCase, Image.SCALE_DEFAULT),
        (i%eltParLigne)*tailleCase,
        (i/eltParLigne)*tailleCase,
        null))

    // dessine la case sélectionnée
    if (caseSelect.isDefined) {
      val p = caseSelect.get

      g.setColor(Color.green)
      g.setStroke(new BasicStroke(6f))

      g.draw(new Rectangle(p._1*tailleCase, p._2*tailleCase, tailleCase, tailleCase))
    }

  }

  def indiceAt(p: (Int,Int)): Int = p._1 + eltParLigne*p._2

  def getEltAt(p: (Int,Int)): TourInvent = {
    listeItems(indiceAt(p))
  }

  def isEltAt(p: (Int,Int)): Boolean = indiceAt(p) < listeItems.length

  /********************* GESTION DES EVENEMENTS *****************/

  listenTo(mouse.clicks)

  reactions += {
    case MouseClicked(_, p, _, _, _) =>
      val pClic: (Int, Int) = (p.getX().toInt/tailleCase, p.getY().toInt/tailleCase)

      if (caseSelect.isDefined && caseSelect.get._1 == pClic._1 && caseSelect.get._2 == pClic._2) {
        caseSelect = None
      }
      else
      {
        caseSelect = Some(pClic)
      }
  }

}
