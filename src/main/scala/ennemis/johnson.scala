/** TowerDefProj
  * johnson.scala
  */


/* PACKAGES */

package ennemis
import jeu._
import affichage.composants._
import affichage.comportements._


class Johnson
    extends Ennemi {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable =


  /* ATTRIBUTS */

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0

  var pvMax: Int = 100
  var vitesse: Double = 
  var portee: Double =1
  var rayon: Double =0
  var deg: Int =30
  var soin: Int =20


  /* METHODES */

  def actTick: Unit = {

  }

  def actMort: Unit = ()

}
