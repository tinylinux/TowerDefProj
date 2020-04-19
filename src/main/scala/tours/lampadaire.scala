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

  var pvMax: Int = 30
  var vitesse: Double = 0
  var portee: Double = 3
  var rayon: Double = 0
  var deg: Int = 0
  var soin: Int = 5
  var cooldownAct: Int = 9

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0


  /* METHODES */

  def actTick: Unit = {
    /* Soigne la tour principale si elle a reçu des dégats */
    if (cooldown == 0) {
      SSoin.soinTourPrincipaleAvecDegats(
        this, cooldownAct
      )
    }

    /* Sinon soigne la tour la plus faible accessible */
    if (cooldown == 0) {
      SSoin.soinPlusFaibleAvecDegats(
        this, carte.tours, cooldownAct
      )
    }
  }

  def actMort: Unit = ()

}
