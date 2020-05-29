/** TowerDefProj
  * type_soignant.scala
  */


/* PACKAGES */

package ennemis
import jeu._
import affichage._
import strategie._
import effets._


case object TypeSoignant
    extends TypeEndommageable {

  /* ATTRIBUTS */

  var img: String = "ennemis/soignant.jpg"

  var nom: String = "SOIGNANT"
  var desc: String = "Il travaille sans relâche pour soigner les personnes vulnérables."


}
