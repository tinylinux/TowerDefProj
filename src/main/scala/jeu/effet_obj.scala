/** TowerDefProj
  * effet_obj.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


object OEffet {

  /* METHODES */

  def ajoutEffet(
    e: Endommageable,
    eff: Effet
  ): Unit = {
    if (!e.effets.exists(
      x => (x.typeE == eff.typeE && x.prio > eff.prio)
    ) ) { // aucun effet appliqué n'est prioritaire sur eff
      e.effets.filter(_.typeE == eff.typeE).foreach(_.fin)
      e.effets = e.effets.filter(_.typeE != eff.typeE)
      e.effets = eff :: e.effets
      eff.cible = e
      eff.debut
    }
  }

  def retirerEffet(
    e: Endommageable,
    eff: Effet
  ): Unit = {
    if (e.effets.exists(_ == eff)) { // l'effet était bien appliqué à e
      eff.fin
      eff.cible = null
      e.effets = e.effets.filter(_ != eff)
    }
  }

}
