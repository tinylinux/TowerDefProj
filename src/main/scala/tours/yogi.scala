/** TowerDefProj
  * yogi.scala
  */


/* PACKAGES */

package tours
import jeu._
import affichage.composants._
import affichage.comportements._


class Yogi
    extends Tour {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable =


  /* ATTRIBUTS */

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0

  var pvMax: Int =70
  var vitesse: Double = 
  var portee: Double =3
  var rayon: Double =1
  var deg: Int =30
  var soin: Int =10


  /* METHODES */

  def actTick: Unit = {

  }

  def actMort: Unit = ()

}
