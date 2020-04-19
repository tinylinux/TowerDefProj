/** TowerDefProj
  * c_zone_grille_a.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._

import java.awt.{Graphics2D, Color, Rectangle, Point}
import scala.swing.Reactions._
import scala.swing.event._



object CZoneGrilleA {


  def react: Reaction = {
    case MouseMoved(_, p, _) =>
      infosTour(p)
    case MouseDragged(_, p, _) =>
      infosTour(p)
  }


  def infosTour(
    p: Point
  ) = {
    if (CPartAff.partie.isDefined) {
      val c = CSelectionneur.pointToCase(ZoneGrille, p)
      CPartAff.partie.get.carte.tours.find(t =>
        t.pos.isDefined && Pos.posToI(t.pos.get) == c) match {
        case Some(t:Tour) => CZoneInfos.infosTour(t)
        case _ => ()
      }
    }
  }


  def updateMax = {
    val (mX, mY) = {
      CPartAff.partie match {
        case Some(p) => (p.carte.maxX, p.carte.maxY)
        case None => (0, 0)
      }
    }
    ZoneGrille.maxX = mX
    ZoneGrille.maxY = mY
  }


  def paintComp(
    g: Graphics2D
  ) = {
    updateMax
    if (CPartAff.partie.isDefined) { // partie chargée
      /* FOND ET TOURS */
      dessineFondTours(g)
      /* ENNEMIS */
      dessineEnnemis(g)
      /* Portée de la tour survolée */
      chercheTourPortee(g)
    }
    CSelectionneur.paintComp(ZoneGrille, g)
  }


  def chercheTourPortee(
    g: Graphics2D
  ) = {
    if (CPartAff.partie.isDefined) {
      val c = CSelectionneur.pointToCase(ZoneGrille, ZoneGrille.posSouris)
      CPartAff.partie.get.carte.tours.find(t =>
        t.pos.isDefined && Pos.posToI(t.pos.get) == c) match {
        case Some(t:Tour) => dessinePortee(g, t.pos.get, t.portee)
        case _ => ()
      }
    }
  }


  def dessineFondTours(
    g: Graphics2D
  ) = {
    val tC = ZoneGrille.tailleCase
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
                (tC, tC) ),
              x, y,
              null)
            dessineBarreVie(g, x, y+tC.toInt, tC.toInt, (tour.pv:Double)/(tour.pvMax:Double))
            dessineBarreCooldown(g, x, y+tC.toInt, tC.toInt, (tour.cooldown:Double)/(tour.cooldownAct:Double))
          }
          case None => {
            // pas de tour sur l'emplacement
            g.drawImage(
              CImgJeu.imgTuile(t(i)(j),
              (tC, tC) ),
              x, y,
              null)
          } }
        y += tC
      }
      y = ZoneGrille.offset._2
      x += tC
    }
  }


  def dessineEnnemis(
    g: Graphics2D
  ) = {
    val tE: Double = 0.8 // rapport entre la taille d'un ennemi et une case
    val o: (Int, Int) = ZoneGrille.offset
    val tC: Int = ZoneGrille.tailleCase
    CPartAff.partie.get.carte.ennemis.foreach(e => {
      if (e.pos.isDefined) {
        // image de l'ennemi
        val x = ((e.pos.get._1-(tE/2))*tC).toInt + o._1
        val y = ((e.pos.get._2-(tE/2))*tC).toInt + o._2
        g.drawImage(
          CImgJeu.imgTypeEndom(
            e.typeE,
            ((tE*tC).toInt, (tE*tC).toInt) ),
          x, y,
          null)
        dessineBarreVie(g, x, y+(tE*tC).toInt, (tE*tC).toInt, (e.pv:Double)/(e.pvMax:Double))
        dessineBarreCooldown(g, x, y+(tE*tC).toInt, (tE*tC).toInt, (e.cooldown:Double)/(e.cooldownAct:Double))
        dessineEffets(g, x, y, (tE*tC).toInt, e.effets)
      }
    } )
  }


  def dessinePortee(
    g: Graphics2D,
    pos: (Double, Double),
    portee: Double
  ) = {
    val oldColor = g.getColor()
    g.setColor(Color.red)
    val centre = CSelectionneur.posToScreen(ZoneGrille, pos)
    val rayon = (portee*ZoneGrille.tailleCase).toInt
    g.drawOval(centre._1-rayon, centre._2-rayon, rayon*2, rayon*2)
    g.setColor(oldColor)
  }


  def dessineBarreVie(
    g: Graphics2D,
    x: Int, // coordonnées du coin en bas à gauche de l'endommageable
    y: Int,
    l: Int, // longueur de la barre de vie (en pixels)
    ratio: Double // ratio pv/pvMax
  ) = {
    val oldColor = g.getColor()
    val lim = (ratio*l).toInt
    val h = l/8+1
    g.setColor(Color.green)
    g.fill(new Rectangle(x,y-h,lim,h))
    g.setColor(Color.red)
    g.fill(new Rectangle(x+lim,y-h,l-lim,h))
    g.setColor(oldColor)
  }


  def dessineBarreCooldown(
    g: Graphics2D,
    x: Int, // coordonnées du coin en bas à gauche de l'endommageable
    y: Int,
    l: Int, // longueur de la barre de cooldown (en pixels)
    ratio: Double // ratio cooldown/cooldownAct
  ) = {
    val oldColor = g.getColor()
    val lim = (ratio*l).toInt
    val h = l/8+1
    g.setColor(Color.blue)
    g.fill(new Rectangle(x,y-h*2,lim,h))
    g.setColor(oldColor)
  }


  def dessineEffets(
    g: Graphics2D,
    x: Int, // coordonnées du coin en haut à gauche de l'endommageable
    y: Int,
    l: Int, // longueur de l'image de l'endommageable (en pixels)
    le: List[Effet] // liste des effets à afficher
  ) = {
    val dx = l/2
    var xE = x
    le.foreach(eff => {
      g.drawImage(
        CImgJeu.imgTypeEffet(
          eff.typeE,
          (dx,dx) ),
        xE, y, null)
      xE += dx
    } )
  }


}
