/** TowerDefProj
  * type_yogi.scala
  */


/* PACKAGES */

package tours
import jeu._
import affichage._
import strategie._
import ennemis._
import effets._


object TypeYogi
    extends TypeEndommageable {

  /* ATTRIBUTS */

  var img: String = "tours/yogi.jpg"

  var nom: String = "YOGI"
  var desc: String = "Lorsqu'il entre en transe, il envoie des attaques dévastatrices à ses ennemis tout en se soignant."


}
