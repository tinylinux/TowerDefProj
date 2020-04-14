/** TowerDefProj
  * c_inventaire.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._


object CPartAff {

  var partie: Option[Partie] = None


  def chargerPartie(
    p: Partie
  ): Unit = {
    partie = Some(p)
    CFinJeu.actuFin
  }
  
}
