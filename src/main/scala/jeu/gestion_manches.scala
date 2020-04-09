/** TowerDefProj
  * gestion_manches.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


abstract class GestionManches {

  /* REFERENCES */

  var partie: Partie
  var m: List[Manche]


  /* ATTRIBUTS */

  var mEnCours: Option[Manche]


  /* METHODES */

  def chargerM: Unit =
    GestionManches.chargerM(this)

}
