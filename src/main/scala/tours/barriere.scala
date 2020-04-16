/** TowerDefProj
  * barriere.scala
  */


/* PACKAGES */

package tours
import jeu._
import affichage.composants._
import affichage.comportements._


class Barriere
    extends Tour {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable =


  /* ATTRIBUTS */

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0

  var pvMax: Int =150
  var vitesse: Double = 
  var portee: Double =0
  var rayon: Double =0
  var deg: Int =0
  var soin: Int =0


  /* METHODES */

  def actTick: Unit = {

  }

  def actMort: Unit = ()

}
