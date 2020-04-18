/** TowerDefProj
  * sniper.scala
  */


/* PACKAGES */

package tours
import jeu._
import strategie._


class Sniper
    extends Tour {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable = TypeSniper


  /* ATTRIBUTS */

  var pvMax: Int = 60
  var vitesse: Double = 0
  var portee: Double = 8
  var rayon: Double = 0
  var deg: Int = 10
  var soin: Int = 0
  var cooldownAct: Int = 7

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0


  /* METHODES */

  def actTick: Unit = {
    if (cooldown == 0) {
      SAttaque.attaqueMeilleurRatio(
        this, carte.ennemis, cooldownAct
      )
    }
  }

  def actMort: Unit = ()

}
