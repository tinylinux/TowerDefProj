/** TowerDefProj
  * type_sniper.scala
  */


/* PACKAGES */

package tours
import jeu._
import affichage._
import strategie._
import ennemis._
import effets._


case object TypeSniper
    extends TypeEndommageable {

  /* ATTRIBUTS */

  var img: String = "tours/sniper.JPG"

  var nom: String = "SNIPER"
  var desc: String = "Il prend son temps pour viser parce qu'il aime le travail bien fait."


}
