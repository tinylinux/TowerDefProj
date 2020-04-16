/** TowerDefProj
  * kamikaze.scala
  */


/* PACKAGES */

package ennemis
import jeu._
import affichage.composants._
import affichage.comportements._


class Kamikaze
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
  var portee: Double =1
  var rayon: Double =3
  var deg: Int =0
  var soin: Int =0


  /* METHODES */

  def actTick: Unit = {

  }

  def actMort: Unit = ()

}
