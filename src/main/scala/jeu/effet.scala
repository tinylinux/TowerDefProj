/** TowerDefProj
  * effet.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


abstract class Effet {

  /* REFERENCES */

  var cible: Endommageable
  var typeE: TypeEffet


  /* ATTRIBUTS */

  var cooldown: Int
  var prio: Int
  var benef: Boolean


  /* METHODES */

  def debut: Unit
  def tick: Unit
  def fin: Unit
  def mort: Unit
  
}
