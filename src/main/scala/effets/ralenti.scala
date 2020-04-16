/** TowerDefProj
  * effet.scala
  */


/* PACKAGES */

package effets
import jeu._
import affichage._
import strategie._
import tours._
import ennemis._


class Ralenti
    extends Effet {

  /* REFERENCES */

  var cible: Endommageable = null

  var typeE: TypeEffet = TypeVitesse


  /* ATTRIBUTS */

  var cooldown: Int = 15
  var prio: Int = 2
  var benef: Boolean = false


  /* METHODES */

  var vitInit: Double = 0.0

  def debut: Unit = {
    vitInit = cible.vitesse
    cible.vitesse /= 2
  }
    
  def tick: Unit = {
    cooldown -= 1
    if (cooldown == 0) fin
  }

  def fin: Unit = {
    cible.vitesse = vitInit
  }

  def mort: Unit = ()
  
}
