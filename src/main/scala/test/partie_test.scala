/** TowerDefProj
  * partie.scala
  */


/* PACKAGES */

package test
import jeu._
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


object PartieTest
    extends Partie {

  /* REFERENCES */

  var gM: GestionManches = GMTest
  var mag: Magasin = MagTest
  var carte: Carte = CarteTest


  /* ATTRIBUTS */

  var argent: Int = 10000
  var gagne: Option[Boolean] = None

}
