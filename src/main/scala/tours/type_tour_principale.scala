/** TowerDefProj
  * tour_principale.scala
  */


/* PACKAGES */

package tours
import jeu._
import affichage._
import strategie._
import ennemis._
import effets._


case object TypeTourPrincipale
    extends TypeEndommageable {

  /* ATTRIBUTS */

  var img: String = "tours/tour_principale.jpg"

  var nom: String = "TOUR PRINCIPALE"
  var desc: String = "Personne n'a jamais su pourquoi les ennemis voulaient la détruire."


}
