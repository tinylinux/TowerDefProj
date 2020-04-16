/** TowerDefProj
  * position_obj.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._

import scala.math.sqrt


object Pos {

  /* METHODES */

  def posToI(
    p: (Double, Double)
  ): (Int, Int) = {
    (p._1.toInt, p._2.toInt)
  }

  def iToPos(
    p: (Int, Int)
  ): (Double, Double) = {
    (p._1, p._2)
  }

  def sPos(
    a: (Double, Double),
    b: (Double, Double)
  ): (Double, Double) = {
    (a._1 + b._1, a._2 + b._2)
  }

  def vect(
    a: (Double, Double),
    b: (Double, Double)
  ): (Double, Double) = {
    (b._1 - a._1, b._2 - a._2)
  }

  def mPos(
    a: (Double, Double),
    m: Double
  ): (Double, Double) = {
    (a._1*m, a._2*m)

  def dist(
    a: (Double, Double),
    b: (Double, Double)
  ): Double = {
    val dx = b._1 - a._1
    val dy = b._2 - a._2
    sqrt(dx*dx + dy*dy)
  }
}
