/** TowerDefProj
  * barriere.scala
  */


/* PACKAGES */

package tours
import jeu._
import strategie._



class Barriere
    extends Tour {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable = TypeBarriere


  /* ATTRIBUTS */

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0

  var pvMax: Int = 150
  var vitesse: Double = 0.0
  var portee: Double = 0.0
  var rayon: Double = 0.0
  var deg: Int = 0
  var soin: Int = 0


  /* METHODES */

  // Une barrière ne fait rien (comme une vraie barrière !)

  def actTick: Unit = ()
  def actMort: Unit = ()

}
