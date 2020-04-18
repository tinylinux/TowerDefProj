/** TowerDefProj
  * johnson.scala
  */


/* PACKAGES */

package ennemis
import jeu._
import strategie._


class Johnson
    extends Ennemi {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable = TypeJohnson


  /* ATTRIBUTS */

  var pvMax: Int = 100
  var vitesse: Double = 0.03
  var portee: Double = 1.0
  var rayon: Double = 0.0
  var deg: Int = 30
  var soin: Int = 20
  var cooldownAct: Int = 12

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0


  /* METHODES */

  def actTick: Unit = {
    /* DEPLACEMENT */
    /* Prend le chemin le plus court, et détruit les tours */
    SDeplacement.deplacementTourPrincipaleOsefTours(this)


    /* ATTAQUE ET SOIN */
    if (cooldown == 0) {
      soigner(soin)
      // se soigne
      SAttaque.attaquePlusProche(
        this, carte.tours, 0
      )
      // attaque la tour la plus proche

      cooldown = cooldownAct
    }
  }

  def actMort: Unit = ()

}
