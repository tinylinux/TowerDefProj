/** TowerDefProj
  * effet.scala
  */


package strategie
import jeu._
import ennemis._
import tours._
import effets._


object SEffet {

  /* STRATEGIES */

  /* EFFETS VERS UN SEUL ENDOMMAGEABLE */


  def effet(
    a: Endommageable,
    c: Endommageable,
    eff: Effet,
    nC: Int
  ) = {
    if (c.pos.isDefined && a.pos.isDefined
      && Pos.dist(a.pos.get, c.pos.get) <= a.portee
      && a.cooldown == 0) {
      OEffet.ajoutEffet(c, eff)
      a.cooldown = nC
    }
  }


  def effetPlusProche(
    e: Endommageable,
    eff: Effet,
    l: List[Endommageable],
    nC: Int // nouveau cooldown après attaque
  ) = {
    if (e.pos.isDefined) {
      SCible.plusProche(
        SCible.filterAPortee(
          SCible.filterHasNoEffect(l, eff.typeE, eff.benef, eff.prio),
          e.pos.get, e.portee),
        e.pos.get
      ) match {
        case None => ()
        case Some(c:Endommageable) => {
          OEffet.ajoutEffet(c, eff)
          e.cooldown = nC
        }
      }
    }
  }



}
