/** TowerDefProj
  * carte_obj.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


object OCarte {

  /* METHODES */

  def moveE(
    c: Carte,
    e: Ennemi,
    pos: (Double, Double)
  ): Unit = {
    if (e.pos.isDefined) {
      e.pos = Some(pos)
    }
  }


  def spawnE(
    c: Carte,
    e: Ennemi,
    pos: (Double, Double)
  ): Unit = {
    e.carte = c
    if (c.ennemis.forall(_ != e)) {
      c.ennemis = e :: c.ennemis
    }

    if (!e.pos.isDefined) {
      e.pos = Some((0.0, 0.0))
    }
    e.moveE(c, e, pos)
  }


  def spawnT(
    c: Carte,
    t: Tour,
    posI: (Int, Int)
  ): Unit = {
    t.carte = c
    if (c.tours.forall(_ != t)) {
      c.tours = t :: c.tours
    }

    if (c.tuiles(posI._1)(posI._2).accesT &&
      c.tours.forall(Pos.posToI(_.pos) != posI)) {
      t.pos = Some(Pos.sPos(
        Pos.iToPos(posI), (0.5, 0.5)
      ))
    }
    else {
      t.pos = None
    }
  }

  def despawnE(
    c: Carte,
    e: Ennemi
  ): Unit = {
    e.pos = None

    e.carte = null
    c.ennemis = c.ennemis.filter(_ != e)
  }

  def despawnT(
    c: Carte,
    t: Tour
  ): Unit = {
    t.pos = None

    t.carte = null
    c.tours = c.tours.filter(_ != t)
    if (t == tP) {
      c.tP = null
    }
  }


  def tick(
    c: Carte
  ): Unit = {
    c.tours.foreach(_.tick)
    c.ennemis.foreach(_.tick)
    c.tours.foreach(
      _.effets.foreach(
        _.tick
      ) )
    c.ennemis.foreach(
      _.effets.foreach(
        _.tick
      ) )
  }


  def posSurCarte(
    c: Carte,
    pos: (Double, Double)
  ): Boolean =
    c.posISurCarte(Pos.posToI(pos))


  def posISurCarte(
    c: Carte,
    posI: (Int, Int)
  ): Boolean =
    0 <= posI._1 && 0 <= posI._2 && posI._1 <= c.maxX && posI._2 <= c.maxY


  def getTourAt(
    c: Carte,
    posI: (Int, Int)
  ): Option[Tour] =
    c.tours.findOption(Pos.posToI(_.pos) == posI)
}
