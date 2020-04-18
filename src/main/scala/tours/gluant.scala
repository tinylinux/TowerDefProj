/** TowerDefProj
  * gluant.scala
  */


/* PACKAGES */

package tours
import jeu._
import strategie._
import effets._


class Gluant
    extends Tour {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable = TypeGluant


  /* ATTRIBUTS */

  var pvMax: Int = 30
  var vitesse: Double = 0.0
  var portee: Double = 2.0
  var rayon: Double = 0.0
  var deg: Int = 0
  var soin: Int = 0
  var cooldownAct: Int = 7

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0

  
  /* METHODES */

  def actTick: Unit = {
    if (cooldown == 0) {
      SEffet.effetPlusProche(
        this, new Ralenti, carte.ennemis, cooldownAct
      ) }
  }

  def actMort: Unit = ()

}
