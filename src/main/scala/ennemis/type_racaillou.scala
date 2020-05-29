/** TowerDefProj
  * type_racaillou.scala
  */


/* PACKAGES */

package ennemis
import jeu._
import affichage._
import strategie._
import effets._


case object TypeRacaillou
    extends TypeEndommageable {

  /* ATTRIBUTS */

  var img: String = "ennemis/racaillou.png"

  var nom: String = "RACAILLOU"
  var desc: String = "Malgré sa taille et sa vitesse c'est un pokémon redoutable !"


}
