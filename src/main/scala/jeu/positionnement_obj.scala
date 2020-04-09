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

}
