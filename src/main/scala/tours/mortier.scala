/** TowerDefProj
  * mortier.scala
  */


/* PACKAGES */

package tours
import jeu._
import affichage.composants._
import affichage.comportements._


class Mortier
    extends Tour {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable = TypeMortier


  /* ATTRIBUTS */

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0

  var pvMax: Int = 40
  var vitesse: Double = 0.0
  var portee: Double = 2.0
  var rayon: Double = 1.0
  var deg: Int = 5
  var soin: Int = 0


  /* METHODES */

  def actTick: Unit = {
    /* AOE sur l'ennemi le plus proche */
    if (cooldown == 0) {
      SAttaque.attaqueAOEPlusProche(
        this, carte.ennemis, 7
      )
  }

  def actMort: Unit = ()

}
