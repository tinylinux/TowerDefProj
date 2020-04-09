/** TowerDefProj
  * carte.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


abstract class Carte {

  /* REFERENCES */

  var partie: Partie
  var tP: Tour
  var tours: List[Tour]
  var ennemis: List[Ennemi]
  var tuiles: Array[Array[Tuile]]


  /* ATTRIBUTS */

  var maxX: Int
  var maxY: Int


  /* METHODES */

  def spawnE(
    e: Ennemi,
    pos: (Double, Double)
  ): Unit =
    OCarte.spawnE(this, e, pos)

  def spawnT(
    t: Tour,
    posI: (Int, Int)
  ): Unit =
    OCarte.spawnT(this, t, posI)

  def moveE(
    e: Ennemi,
    pos: (Double, Double)
  ): Unit =
    OCarte.moveE(this, e, pos)

  def despawnE(
    e: Ennemi
  ): Unit =
    OCarte.despawnE(this, e)

  def despawnT(
    t: Tour
  ): Unit =
    OCarte.despawnT(this, t)

  def tick: Unit =
    OCarte.tick(this)

  def posSurCarte(
    pos: (Double, Double)
  ): Boolean =
    OCarte.posSurCarte(this, pos)

  def posISurCarte(
    posI: (Int, Int)
  ): Boolean =
    OCarte.posISurCarte(this, posI)

  def getTourAt(
    posI: (Int, Int)
  ): Option[Tour] =
    OCarte.getTourAt(this, posI)

}
