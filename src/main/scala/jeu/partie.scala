/** TowerDefProj
  * partie.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


abstract class Partie {

  /* REFERENCES */

  // TODO affichage
  var gM: GestionManches
  var mag: Magasin
  var carte: Carte


  /* ATTRIBUTS */

  var argent: Int
  var gagne: Some[Boolean]


  /* METHODES */

  def actuGagne: Unit
  def acheter(
    idContrat: Int,
    posI: (Int, Int)
  ): Unit

  def tick: Unit


}
