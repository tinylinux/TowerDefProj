/** TowerDefProj
  * c_selectionneur.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._

import scala.swing._
import scala.swing.Reactions._
import scala.swing.event._
import java.awt.{Point, Color, Graphics2D, BasicStroke, Rectangle}


object CSelectionneur {

  def pointToCase(
    s: Selectionneur,
    p: Point
  ): (Int, Int) = { (
    (p.getX().toInt - s.offset._1)/s.tailleCase,
    (p.getY().toInt - s.offset._2)/s.tailleCase)
  }


  def inBounds(
    s: Selectionneur,
    c: (Int, Int)
  ): Boolean =
    0 <= c._1 && 0 <= c._2 && c._1 < s.maxX && c._2 < s.maxY


  def react(
    s: Selectionneur
  ): Reaction = {
    case MouseClicked(_, p, _, _, _) =>
      val c = pointToCase(s,p)
      if (!inBounds(s,c)) {
        s.caseSel = None
      }
      else {
        if (s.caseSel.isDefined && s.caseSel.get == c) {
          s.caseSel = None
        }
        else {
          s.caseSel = Some(c)
        }
      }



    case MouseEntered(_, _, _) =>
      s.sourisSurSel = true

    case MouseExited(_, _, _) =>
      s.sourisSurSel = false

    case MouseMoved(_, p, _) =>
      s.posSouris = p

    case MouseDragged(_, p, _) =>
      s.posSouris = p
  }



  def paintComp(
    s: Selectionneur,
    g: Graphics2D
  ) = {
    /* Sauvegarde anciens paramètres */
    val oldColor = g.getColor()
    val oldStroke = g.getStroke()

    /* Case sélectionnée */
    if (s.caseSel.isDefined) {
      val xCS = s.offset._1 + s.caseSel.get._1*s.tailleCase
      val yCS = s.offset._2 + s.caseSel.get._2*s.tailleCase

      g.setColor(Color.green)
      g.setStroke(new BasicStroke(6f))

      g.draw(new Rectangle(
        xCS, yCS, s.tailleCase, s.tailleCase))
    }

    /* Case survolée */
    val c = pointToCase(s,s.posSouris)
    if (s.sourisSurSel && inBounds(s,c)) {
      val xC = s.offset._1 + c._1*s.tailleCase
      val yC = s.offset._2 + c._2*s.tailleCase

      g.setColor(Color.red)
      g.setStroke(new BasicStroke(6f))

      g.draw(new Rectangle(
        xC, yC, s.tailleCase, s.tailleCase))
    }

    /* Restaurer anciens paramètres */
    g.setColor(oldColor)
    g.setStroke(oldStroke)

  }

}
