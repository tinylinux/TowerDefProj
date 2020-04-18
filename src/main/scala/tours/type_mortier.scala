/** TowerDefProj
  * type_mortier.scala
  */


/* PACKAGES */

package tours
import jeu._
import affichage._
import strategie._
import ennemis._
import effets._


object TypeMortier
    extends TypeEndommageable {

  /* ATTRIBUTS */

  var img: String = "tours/mortier.jpg"

  var nom: String = "MORTIER"
  var desc: String = "Ses obus font des dégâts importants aux ennemis à proximité de l'impact."


}
