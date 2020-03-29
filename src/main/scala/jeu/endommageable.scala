
/** PROJET PROG 2
  * jeu.scala
  */

package jeu

import affichage._


object Endommageable {

  def supprimerMorts[A <: Endommageable](l : List[A]) =
    l.filter(e => !e.mort)

}

/* MODIFICATION : AJOUT DE POS ET PORTEE À LA CLASSE ENDOMMAGEABLE
 * 
 * Ces modifications seront à prendre en compte quand
 * on aura reréfléchi à une nouvelle arborescence des classes
 * (APRÈS avoir fini tout ce qui est demandé)


class Endommageable {

  val pvMax: Int
  var pv: Int = pvMax

  def infliger(d: Int): Int = {
    val pvInit = pv
    if (d >= 0)
      pv -= d
    if (pv < 0)
      pv = 0
    return pv-pvInit
  }

  var pos: Option[(Double, Double)] = None
  var portee: Double
  def posInt: Option[(Int, Int)] = { pos match {
      case None => None
      case Some(x, y) => Some(x.toInt, y.toInt)
  } }

  def mort: Boolean = (pv == 0)
  def horsCarte: Boolean = !pos.isDefined
  def horsJeu: Boolean = mort || horsCarte
}

 */


abstract class Endommageable {

  val pvMax: Int
  var pv: Int = pvMax

  def infliger(d: Int): Int = {
    val pvInit = pv
    if (d >= 0)
      pv -= d
    if (pv < 0)
      pv = 0
    return pv-pvInit
  }

  def mort: Boolean = (pv == 0)
}
