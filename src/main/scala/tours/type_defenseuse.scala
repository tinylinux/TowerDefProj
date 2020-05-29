/** TowerDefProj
  * type_defenseuse.scala
  */


/* PACKAGES */

package tours
import jeu._
import affichage._
import strategie._
import ennemis._
import effets._


case object TypeDefenseuse
    extends TypeEndommageable {

  /* ATTRIBUTS */

  var img: String = "tours/defenseuse.jpg"

  var nom: String = "DEFENSEUSE"
  var desc: String = "Elle travaille dur pour préparer la Coupe de France de football féminin."


}
