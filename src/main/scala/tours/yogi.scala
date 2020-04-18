/** TowerDefProj
  * yogi.scala
  */


/* PACKAGES */

package tours
import jeu._
import strategie._

class Yogi
    extends Tour {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable = TypeYogi


  /* ATTRIBUTS */

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0

  var pvMax: Int = 70
  var vitesse: Double = 0.0
  var portee: Double = 3.0
  var rayon: Double = 1.0
  var deg: Int = 30
  var soin: Int = 10
  var cooldownAct: Int = 12


  /* METHODES */

  def actTick: Unit = {
    /* ATTAQUE ET AUTO-SOIN */
    if (cooldown == 0) {
      soigner(soin)
      SAttaque.attaquePlusProche(
        this, carte.ennemis, 0
      )
      cooldown = cooldownAct
    }
  }

  def actMort: Unit = ()

}
