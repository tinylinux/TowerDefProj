/** TowerDefProj
  * racaillou.scala
  */


/* PACKAGES */

package ennemis
import jeu._
import strategie._


class Racaillou
    extends Ennemi {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable = TypeRacaillou


  /* ATTRIBUTS */

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0

  var pvMax: Int = 20
  var vitesse: Double = 0.08
  var portee: Double = 1.0
  var rayon: Double = 0.0
  var deg: Int = 10
  var soin: Int = 0


  /* METHODES */

  def actTick: Unit = {
    /* DEPLACEMENT */
    /* Prend le chemin le plus court, et détruit les tours */
    SDeplacement.deplacementTourPrincipaleOsefTours(this)


    /* ATTAQUE */
    /* ATTAQUE DE LA TOUR LA PLUS PROCHE */
    if (cooldown == 0) {
      SAttaque.attaquePlusProche(
        this, carte.tours, 5
      )
    }
  }

  def actMort: Unit = ()

}
