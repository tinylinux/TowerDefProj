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

  def actuFin: Unit =
    OPartie.actuFin(this)

  def acheter(
    idContrat: Int,
    posI: (Int, Int)
  ): Unit =
    OPartie.acheter(this, idContrat, posI)

  def tick: Unit =
    OPartie.tick(this)

}
