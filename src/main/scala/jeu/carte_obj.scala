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
      if (posIAccesE(c, Pos.posToI(pos)))
        // la position à atteindre est accessible à un ennemi
        e.pos = Some(pos)
      else {
        /* la position à atteindre n'est pas accessible
         * l'ennemi est amené au bord de sa case courante,
         * proche de la position à atteindre
         */
        val eps = 0.01
        val cE = Pos.posToI(e.pos.get)
        val cD = Pos.posToI(pos)
        var (x,y) = e.pos.get

        // calcul x
        if (cE._1 == cD._1)
          x = pos._1
        else if (cE._1 < cD._1)
          x = 1.0 - eps
        else
          x = eps

        // calcul y
        if (cE._2 == cD._2)
          y = pos._2
        else if (cE._2 < cD._2)
          y = 1.0 - eps
        else
          y = eps

        e.pos = Some((x,y))
      }
    }
  }


  def spawnE(
    c: Carte,
    e: Ennemi,
    pos: (Double, Double)
  ): Unit = {
    e.carte = c
    if (c.ennemis.forall(x => x != e)) {
      c.ennemis = e :: c.ennemis
    }

    if (!e.pos.isDefined) {
      e.pos = Some((0.0, 0.0))
    }
    c.moveE(e, pos)
  }


  def spawnT(
    c: Carte,
    t: Tour,
    posI: (Int, Int)
  ): Unit = {
    t.carte = c
    if (c.tours.forall(x => x != t)) {
      c.tours = t :: c.tours
    }

    if (c.tuiles(posI._1)(posI._2).accesT &&
      c.tours.forall(x => (!x.pos.isDefined)
        || Pos.posToI(x.pos.get) != posI)) {
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
    c.ennemis = c.ennemis.filter(x => x != e)
  }

  def despawnT(
    c: Carte,
    t: Tour
  ): Unit = {
    t.pos = None

    t.carte = null
    c.tours = c.tours.filter(x => x != t)
    if (t == c.tP) {
      c.tP = null
    }
  }


  def tick(
    c: Carte
  ): Unit = {
    c.tours.foreach(x => x.tick)
    c.ennemis.foreach(x => x.tick)
    c.tours.foreach(
      x => x.effets.foreach(
        y => y.tick
      ) )
    c.ennemis.foreach(
      x => x.effets.foreach(
        y => y.tick
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
    c.tours.find(x => x.pos.isDefined
      && Pos.posToI(x.pos.get) == posI)

  def posIAccesE(
    c: Carte,
    posI: (Int, Int)
  ): Boolean =
    c.tuiles(posI._1)(posI._2).accesE && c.tours.forall(t =>
      (!t.pos.isDefined) || Pos.posToI(t.pos.get) != posI)

}
