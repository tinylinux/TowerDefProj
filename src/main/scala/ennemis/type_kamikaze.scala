/** TowerDefProj
  * type_kamikaze.scala
  */


/* PACKAGES */

package ennemis
import jeu._
import affichage._
import strategie._
import effets._


object TypeKamikaze
    extends TypeEndommageable {

  /* ATTRIBUTS */

  var img: String = "ennemis/kamikaze.jpg"

  var nom: String = "KAMIKAZE"
  var desc: String = "Il transporte des explosifs (peut être juste pour les offrir comme cadeau)"


}
