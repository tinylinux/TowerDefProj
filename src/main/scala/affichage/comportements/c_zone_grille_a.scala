/** TowerDefProj
  * c_zone_grille_a.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._

import java.awt.Graphics2D



object CZoneGrilleA {

  def paintComp(
    g: Graphics2D
  ) = {
    if (CPartAff.partie.isDefined) { // partie chargée
      /* FOND ET TOURS */
      dessineFondTours(g)

      /* ENNEMIS */
      dessineEnnemis(g)
    }
  }


  def dessineFondTours(
    g: Graphics2D
  ) = {
    var t = CPartAff.partie.get.carte.tuiles
    var x = ZoneGrille.offset._1
    var y = ZoneGrille.offset._2
    for (i <- 0 to t.length - 1) {
      for (j <- 0 to t(i).length - 1) {
        CPartAff.partie.get.carte.getTourAt((i,j)) match {
          case Some(tour: Tour) => {
            // tour sur l'emplacement
            g.drawImage(
              CImgJeu.imgTypeEndom(
                tour.typeE,
                (ZoneGrille.tailleCase, ZoneGrille.tailleCase) ),
              x, y,
              null)
          }
          case None => {
            // pas de tour sur l'emplacement
            g.drawImage(
              CImgJeu.imgTuile(t(i)(j),
              (ZoneGrille.tailleCase, ZoneGrille.tailleCase) ),
              x, y,
              null)
          } }
        x += ZoneGrille.tailleCase
      }
      x = ZoneGrille.offset._1
      y += ZoneGrille.tailleCase
    }
  }


  def dessineEnnemis(
    g: Graphics2D
  ) = {
    val tE: Double = 1/2 // rapport entre la taille d'un ennemi et une case
    CPartAff.partie.get.carte.ennemis.foreach(e => {
      if (e.pos.isDefined) {
        // image de l'ennemi
        val x = ((e.pos.get._1-(tE/2))*ZoneGrille.tailleCase).toInt + ZoneGrille.offset._1
        val y = ((e.pos.get._2-(tE/2))*ZoneGrille.tailleCase).toInt + ZoneGrille.offset._2
        g.drawImage(
          CImgJeu.imgTypeEndom(
            e.typeE,
            (ZoneGrille.tailleCase, ZoneGrille.tailleCase) ),
          x, y,
          null)
      }
    } )
  }


}
