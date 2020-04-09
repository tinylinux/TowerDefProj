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
  var enn: List[Ennemi]
  var tuiles: Array[Array[Tuile]]


  /* ATTRIBUTS */

  var maxX: Int
  var maxY: Int


  /* METHODES */

  def spawnE(
    pos: (Double, Double)
  ): Unit =
    Carte.spawnE(this, e, pos)

  def spawnT(
    posI: (Int, Int)
  ): Unit =
    Carte.spawnT(this, t, posI)

  def moveE(
    e: Ennemi
    pos: (Double, Double)
  ): Unit =
    Carte.moveE(this, e, pos)

  def despawnE(
    e: Ennemi
  ): Unit =
    Carte.despawnE(this, e)

  def despawnT(
    t: Tour
  ): Unit =
    Carte.despawnT(this, t)

  def tick: Unit =
    Carte.tick(this)

  def posSurCarte(
    pos: (Double, Double)
  ): Boolean =
    Carte.posSurCarte(this, pos)

  def posISurCarte(
    posI: (Int, Int)
  ): Boolean =
    Carte.posISurCarte(this, posI)

  def getTourAt(
    posI: (Int, Int)
  ): Option[Tour] =
    Carte.getTourAt(this, posI)

}
