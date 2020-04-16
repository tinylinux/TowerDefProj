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

  var pv: Int // PV (actuels) de l'endommageable
  var pvMax: Int // PV max
  var pos: Option[(Double, Double)]
  var vitesse: Double // vitesse de déplacement (en distance/tick)
  var portee: Double // portée des attaques/soin
  var rayon: Double // rayon (pour les dégâts/soins de zone)
  var deg: Int // nb de infligés aux autres endommageables
  var soin: Int // nb de pv soignés aux autres endommageables
  var cooldown: Int // durée restante avant la prochaine action (en nb de tick)


  /* METHODES */

  def degats(
    d: Int
  ): Unit =
    OEndommageable.degats(this, d)

  def soigner(
    s: Int
  ): Unit =
    OEndommageable.soin(this, s)

  def ajoutEffet(
    eff: Effet
  ): Unit =
    OEffet.ajoutEffet(this, eff)

  def retirerEffet(
    eff: Effet
  ): Unit =
    OEffet.ajoutEffet(this, eff)

  def tick: Unit =
    OEndommageable.tick(this)

  def despawn: Unit

  
  def actTick: Unit
  def actMort: Unit

}
