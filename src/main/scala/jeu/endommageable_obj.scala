/** TowerDefProj
  * endommageable_obj.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


object OEndommageable {

  /* METHODES */

  def actuPv(
    e: Endommageable
  ): Unit = {
    if (e.pv > e.pvMax) { e.pv = e.pvMax }
    if (e.pv < 0) { e.pv = 0 }
    if (e.pv == 0) { OEndommageable.mourir(e) }
  }

  def degats(
    e: Endommageable,
    d: Int
  ): Unit = {
    e.pv -= d
    OEndommageable.actuPv(e)
  }

  def soin(
    e: Endommageable,
    s: Int
  ): Unit = {
    e.pv += s
    OEndommageable.actuPv(e)
  }

  def mourir(
    e: Endommageable
  ): Unit = {
    e.effets.foreach(_.mort)
    e.actMort
    e.despawn
  }

  def tick(
    e: Endommageable
  ): Unit = {
    if (e.cooldown > 0) { e.cooldown -= 1 }
    e.actTick
  }
}
