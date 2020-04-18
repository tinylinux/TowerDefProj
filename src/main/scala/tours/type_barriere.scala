/** TowerDefProj
  * type_barriere.scala
  */


/* PACKAGES */

package tours
import jeu._
import affichage._
import strategie._
import ennemis._
import effets._


object TypeBarriere
    extends TypeEndommageable {

  /* ATTRIBUTS */

  var img: String = "tours/barriere.jpg"

  var nom: String = "BARRIERE"
  var desc: String = "Elle est très intimidante, personne n'ose l'escalader."


}
