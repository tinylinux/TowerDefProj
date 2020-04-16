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

  var typeE: TypeEndommageable = TypeSoignant


  /* ATTRIBUTS */

  var pv: Int = pvMax
  var pos: Option[(Double, Double)] = None
  var cooldown: Int = 0

  var pvMax: Int = 20
  var vitesse: Double = 
  var portee: Double = 2.0
  var rayon: Double = 0.0
  var deg: Int = 0
  var soin: Int = 3


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
        this, carte.ennemis, 3
      )
    }
  }

  def actMort: Unit = ()

}
