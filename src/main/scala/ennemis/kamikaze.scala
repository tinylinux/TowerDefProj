/** TowerDefProj
  * kamikaze.scala
  */


/* PACKAGES */

package ennemis
import jeu._
import strategie._


class Kamikaze
    extends Ennemi {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable = TypeKamikaze


  /* ATTRIBUTS */

  var pvMax: Int = 20
  var vitesse: Double = 0.2
  var portee: Double = 0.8
  var rayon: Double = 2.0
  var deg: Int = 20
  var soin: Int = 0
  var cooldownAct: Int = 0

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0


  /* METHODES */

  def actTick: Unit = {
    /* DEPLACEMENT : CHERCHE À ÉVITER LES TOURS */
    SDeplacement.deplacementTourPrincipale(this)

    /* SUICIDE : S'IL EST SUFFISAMMENT PROCHE DE LA TOUR PRINCIPALE */
    if (SCible.aPortee(carte.tP, pos.get, portee))
      // la tour principale est à portée d'explosion
      OEndommageable.mourir(this)
  }

  var explose = false // le kamikaze a déjà explosé

  def actMort: Unit = {
    if (!explose) {
      explose = true
      SAttaque.attaqueAOEPos(
        this, pos.get, carte.tours, 0)
      SAttaque.attaqueAOEPos(
        this, pos.get, carte.ennemis.filter(_ != this), 0)
      // Explosion qui attaque tous les ennemis aux alentours (à portée)
    }
  }

}
