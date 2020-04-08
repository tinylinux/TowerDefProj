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


}
