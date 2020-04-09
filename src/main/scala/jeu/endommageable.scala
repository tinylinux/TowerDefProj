/** TowerDefProj
  * endommageable.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


abstract class Endommageable {

  /* REFERENCES */

  var carte: Carte
  var effets: List[Effet]
  var typeE: TypeEndommageable


  /* ATTRIBUTS */

  var pv: Int
  var pvMax: Int
  var pos: Option[(Double, Double)]
  var vitesse: Double
  var portee: Double
  var rayon: Double
  var deg: Int
  var soin: Int
  var cooldown: Int


  /* METHODES */

  def degats(
    d: Int
  ): Unit =
    Endommageable.degats(this, d)

  def soin(
    s: Int
  ): Unit =
    Endommageable.soin(this, d)

  def ajoutEffet(
    eff: Effet
  ): Unit =
    Effet.ajoutEffet(this, eff)

  def retirerEffet(
    eff: Effet
  ): Unit =
    Effet.ajoutEffet(this, eff)

  def tick: Unit =
    Endommageable.tick(this)

  def actTick: Unit
  def actMort: Unit

}
