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


  /* ATTRIBUTS */

  var cooldown: Option[Int]
  var prio: Int
  var benef: Boolean


  /* METHODES */


}
