/** TowerDefProj
  * tour_principale.scala
  */


/* PACKAGES */

package tours
import jeu._
import strategie._


class TourPrincipale
    extends Tour {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable = TypeTourPrincipale


  /* ATTRIBUTS */

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0

  var pvMax: Int = 100
  var vitesse: Double = 0.0
  var portee: Double = 1.0
  var rayon: Double = 0.0
  var deg: Int = 5
  var soin: Int = 0


  /* METHODES */

  /** La TourPrincipale ne se déplace pas, elle attaque simplement
    * l'ennemi le plus proche (s'il y en a un à portée)
    */
  def actTick: Unit = {
    /* ATTAQUE DE L'ENNEMI LE PLUS PROCHE */
    if (cooldown == 0) {
      SAttaque.attaquePlusProche(
        this, carte.ennemis, 5
      )
    }
  }

  def actMort: Unit = ()

  
}
