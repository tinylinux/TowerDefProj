/** TowerDefProj
  * Fourmi.scala
  */


/* PACKAGES */

package ennemis
import jeu._
import strategie._


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
  var portee: Double = 1.0
  var rayon: Double = 0.0
  var deg: Int = 2
  var soin: Int = 0


  /* METHODES */

  def actTick: Unit = {
    /* DEPLACEMENT */
    /* D'abord en contournant les tours */
    if (!SDeplacement.deplacementTourPrincipale(this)) {
      /* Si ça a échoué, en passant par les chemins avec tours
       * (il faudra les détruire)
       */
      SDeplacement.deplacementTourPrincipaleOsefTours(this)
    }


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
