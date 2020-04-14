/** TowerDefProj
  * NOM_ENNEMI.scala
  */


/* PACKAGES */

package ennemis
import jeu._
import affichage.composants._
import affichage.comportements._


class NOM_ENNEMI
    extends Ennemi {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable =


  /* ATTRIBUTS */

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0

  var pvMax: Int = 
  var vitesse: Double = 
  var portee: Double =
  var rayon: Double =
  var deg: Int =
  var soin: Int =


  /* METHODES */

  def actTick: Unit = {

  }

  def actMort: Unit = ()

}
