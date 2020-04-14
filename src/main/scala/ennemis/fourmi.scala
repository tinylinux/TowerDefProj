/** TowerDefProj
  * Fourmi.scala
  */


/* PACKAGES */

package ennemis
import jeu._
import affichage.composants._
import affichage.comportements._


class Fourmi
    extends Ennemi {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable = TypeFourmi


  /* ATTRIBUTS */

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0

  var pvMax: Int = 5
  var vitesse: Double = 0.3
  var portee: Double = 1
  var rayon: Double = 0
  var deg: Int = 2
  var soin: Int = 0


  /* METHODES */

  def actTick: Unit = {
    /* DEPLACEMENT */
// A FAIRE



    /* ATTAQUE */
    /* ATTAQUE DE LA TOUR PRINCIPALE (SI À PORTÉE) */
    if (cooldown == 0) {
      SAttaque.attaqueTourPrincipale(
        this, 3
      )
    }
    /* SINON, ATTAQUE DE LA TOUR LA PLUS PROCHE */
    if (cooldown == 0) {
      SAttaque.attaquePlusProche(
        this, carte.tours, 5
      )
    }
  }

  def actMort: Unit = ()

}
