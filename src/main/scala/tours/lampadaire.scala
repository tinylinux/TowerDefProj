/** TowerDefProj
  * lampadaire.scala
  */


/* PACKAGES */

package tours
import jeu._
import strategie._


class Lampadaire
    extends Tour {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable = TypeLampadaire


  /* ATTRIBUTS */

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0

  var pvMax: Int = 30
  var vitesse: Double = 0
  var portee: Double = 4
  var rayon: Double = 0
  var deg: Int = 0
  var soin: Int = 5


  /* METHODES */

  def actTick: Unit = {
    /* Soigne la tour principale si elle a reçu des dégats */
    if (cooldown == 0) {
      SSoin.soinTourPrincipaleAvecDegats(
        this, 4
      )
    }

    /* Sinon soigne la tour la plus faible accessible */
    if (cooldown == 0) {
      SSoin.soinPlusFaibleAvecDegats(
        this, carte.tours, 4
      )
    }
  }

  def actMort: Unit = ()

}
