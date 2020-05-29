/** TowerDefProj
  * type_johnson.scala
  */


/* PACKAGES */

package ennemis
import jeu._
import affichage._
import strategie._
import effets._


case object TypeJohnson
    extends TypeEndommageable {

  /* ATTRIBUTS */

  var img: String = "ennemis/johnson.jpg"

  var nom: String = "JOHNSON"
  var desc: String = "Il a survécu au COVID-19 !!"


}
