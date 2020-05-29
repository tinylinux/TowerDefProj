/** TowerDefProj
  * lampadaire.scala
  */


/* PACKAGES */

package tours
import jeu._
import affichage._
import strategie._
import ennemis._
import effets._


case object TypeLampadaire
    extends TypeEndommageable {

  /* ATTRIBUTS */

  var img: String = "tours/lampadaire.jpg"

  var nom: String = "LAMPADAIRE"
  var desc: String = "Depuis qu'il s'est fait aspirer par Dumbledore, il n'est plus tout à fait le même."


}
