/** TowerDefProj
  * soignant.scala
  */


/* PACKAGES */

package ennemis
import jeu._
import strategie._


class Soignant
    extends Ennemi {

  /* REFERENCES */

  var carte: Carte = null
  var effets: List[Effet] = Nil

  var typeE: TypeEndommageable = TypeSoignant


  /* ATTRIBUTS */

  var pvMax: Int = 20
  var vitesse: Double = 0.08
  var portee: Double = 2.0
  var rayon: Double = 0.0
  var deg: Int = 0
  var soin: Int = 3
  var cooldownAct: Int = 3

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0


  /* METHODES */

  def actTick: Unit = {
    /* DEPLACEMENT */
    /* Se déplace vers l'ennemi le plus faible n'ayant pas
     * tous ses pv
     */
    SDeplacement.deplacementEnnemiPlusFaibleAvecDegats(this)


    /* SOIN */
    /* SOIN DE L'ENNEMI À SOIGNER LE PLUS FAIBLE AVEC DEGATS
     * (à portée)
     */
    if (cooldown == 0) {
      SSoin.soinPlusFaibleAvecDegats(
        this, carte.ennemis, cooldownAct
      )
    }
  }

  def actMort: Unit = ()

}
