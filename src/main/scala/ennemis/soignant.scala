/** TowerDefProj
  * soignant.scala
  */


/* PACKAGES */

package ennemis
import jeu._
import affichage.composants._
import affichage.comportements._


class Soignant
    extends Ennemi {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable =


  /* ATTRIBUTS */

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0

  var pvMax: Int = 20
  var vitesse: Double = 
  var portee: Double =2
  var rayon: Double =0
  var deg: Int =0
  var soin: Int =3


  /* METHODES */

  def actTick: Unit = {

  }

  def actMort: Unit = ()

}
