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
        DimJeu.hInventaire-(CPartAff.partie.get.mag.contrats.length/2)*Inventaire.tailleCase
      if (Inventaire.offsetMax > 0) { Inventaire.offsetMax = 0 }
    }
    else {
      Inventaire.offsetMax = 0
    }

    /* Offset */
    if (Inventaire.offset._2 < Inventaire.offsetMax) {
      Inventaire.offset = (Inventaire.offset._1, Inventaire.offsetMax)
    }
    if (Inventaire.offset._2 > 0) {
      Inventaire.offset = (Inventaire.offset._1, 0)
    }
  }


  def react: Reaction = {
    case MouseWheelMoved(_, _, _, d) => {
      if (d == 1) { // d==1 : vers le haut
        Inventaire.offset =
          (Inventaire.offset._1,
            Inventaire.offset._2+Inventaire.dOffset)
      }
      else { // d==-1 : vers le bas
        Inventaire.offset =
          (Inventaire.offset._1,
            Inventaire.offset._2-Inventaire.dOffset)
      }
      updateOffset
    }


    case MouseMoved(_, p, _) =>
      infosContrat(p)

    case MouseDragged(_, p, _) =>
      infosContrat(p)

  }


  def infosContrat(
    p: Point
  ) = {
    if (CPartAff.partie.isDefined) {
      val (x,y) = CSelectionneur.pointToCase(Inventaire, p)
      CPartAff.partie.get.mag.getContrat(2*y+x) match {
        case Some(c:Contrat) => CZoneInfos.infosContrat(c)
        case _ => ()
      }
    }
  }




  /* AFFICHAGE */

  // met à jour offset et max
  def paintComp(
    g: Graphics2D
  ) = {
    updateMax
    updateOffset
    if (CPartAff.partie.isDefined) {
      var c = CPartAff.partie.get.mag.contrats
      var x = Inventaire.offset._1 // = 0
      var y = Inventaire.offset._2
      def aff(
        l: List[Contrat]
      ): Unit = {
        l match {
          case Nil => ()
          case e :: l2 =>
            g.drawImage(
              CImgJeu.imgTypeEndom(
                e.typeNouv,
                (Inventaire.tailleCase,
                  Inventaire.tailleCase) ),
              x, y,
              null)
            if (x == Inventaire.offset._1) {
              x += Inventaire.tailleCase
            }
            else {
              x = Inventaire.offset._1
              y += Inventaire.tailleCase
            }
            aff(l2)
        } }
      aff(c)
    }
    CSelectionneur.paintComp(Inventaire, g)
  }



}
