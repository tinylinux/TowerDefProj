/** TowerDefProj
  * c_inventaire.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._

import scala.swing._
import scala.swing.Reactions._
import scala.swing.event._
import java.awt.{Point, Graphics2D}



object CInventaire {


  /* EVENEMENTS */

  def updateMax: Unit = {
    if (CPartAff.partie.isDefined) {
      Inventaire.maxX = 2
      Inventaire.maxY =
        (CPartAff.partie.get.mag.contrats.length+1)/2
    }
    else {
      Inventaire.maxX = 0
      Inventaire.maxY = 0
      Inventaire.caseSel = None
    }
  }


  def updateOffset: Unit = {
    /* Offset Max */
    if (CPartAff.partie.isDefined) {
      Inventaire.offsetMax =
        DimJeu.hInventaire-(CPartAff.partie.mag.contrats.length/2)*Inventaire.tailleCase
      if (Inventaire.offsetMax > 0) { Inventaire.offsetMax = 0 }
    }
    else {
      Inventaire.offsetMax = 0
    }

    /* Offset */
    if (Inventaire.offset._2 < Inventaire.offsetMax) {
      Inventaire.offset._2 = Inventaire.offsetMax
    }
    if (Inventaire.offset._2 > 0) {
      Inventaire.offset._2 = 0
    }
  }


  def react: Reaction = {
    case MouseWheelMoved(_, _, _, d) {
// test
print("MouseWheelMoved " + d.toString)

      if (d == 1) { // vers le haut
        Inventaire.offset += Inventaire.dOffset
      }
      else { // vers le bas
        Inventaire.offset -= Inventaire.dOffset
      }
      updateOffset
    }

  }




  /* AFFICHAGE */

  // met à jour offset et max
  def paintComp(
    g: Graphics2D
  ) = {
    updateMax
    updateOffset
    if (PartAff.partie.isDefined) {
      var c = PartAff.partie.get.mag.contrats
      var x = offset._1 // = 0
      var y = offset._2
      def aff(
        l: List[Contrat]
      ): Unit = {
        l match {
          case Nil => ()
          case e :: l2 =>
            g.drawImage(
              x, y,
              CImgJeu.imgTypeEndom(
                e.typeNouv.imgMag,
                (Inventaire.tailleCase,
                  Inventaire.tailleCase) ),
              null)
            if (x == offset._1) {
              x += tailleCase
            }
            else {
              x = offset._1
              y += tailleCase
            }
            aff(l2)
        } }
      aff(c)
    }
  }



}
