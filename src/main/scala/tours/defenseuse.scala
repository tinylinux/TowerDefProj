/** TowerDefProj
  * defenseuse.scala
  */


/* PACKAGES */

package tours
import jeu._
import strategie._


class Defenseuse
    extends Tour {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable = TypeDefenseuse


  /* ATTRIBUTS */

  var pvMax: Int = 40
  var vitesse: Double = 0.0
  var portee: Double = 2.0
  var rayon: Double = 0.0
  var deg: Int = 10
  var soin: Int = 0
  var cooldownAct: Int = 4

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0


  /* METHODES */

  def actTick: Unit = {
    /* ATTAQUE DE L'ENNEMI LE PLUS PROCHE */
    if (cooldown == 0) {
      SAttaque.attaquePlusProche(
        this, carte.ennemis, cooldownAct
      ) }
  }


  def actMort: Unit = ()

}
