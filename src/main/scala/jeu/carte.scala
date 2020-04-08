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
  ): Unit

  def spawnT(
    posI: (Int, Int)
  ): Unit

  def moveE(
    pos: (Double, Double)
  ): Unit

  def despawnE(
    e: Ennemi
  ): Unit

  def despawnT(
    t: Tour
  ): Unit

  def tick: Unit
}
