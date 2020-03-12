
/** PROJET PROG 2
  * jeu.scala
  */

package jeu

import affichage._


object Endommageable {

  def supprimerMorts[A <: Endommageable](l : List[A]) = l.filter(e => !e.mort)

}

class Endommageable(
  val pvMax: Int,
) {
  var pv : Int = pvMax

  def infligerDegats(d : Int) : Int = {
    val pvInit = pv
    if (d >= 0)
      pv -= d
    if (pv < 0)
      pv = 0
    return pv-pvInit
  }

  def mort: Boolean = (pv == 0)

}
