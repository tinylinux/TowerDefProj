/** TowerDefProj
  * soin.scala
  */


package strategie
import jeu._
import ennemis._
import tours._


object SSoin {

  /* STRATEGIES */

  /* SOINS INDIVIDUELS */

  def soinPlusFaibleAvecDegats(
    e: Endommageable,
    l: List[Endommageable],
    nC: Int // nouveau cooldown après attaque
  ) = {
    if (e.pos.isDefined) {
      SCible.plusFaibleAvecDegats(
        SCible.filterAPortee(l, e.pos.get, e.portee)
      ) match {
        case None => ()
        case Some(c:Endommageable) => {
          c.soigner(e.soin)
          e.cooldown = nC
        }
      }
    }
  }

  def soin(
    s: Endommageable,
    c: Endommageable,
    nC: Int
  ) = {
    if (c.pos.isDefined && s.pos.isDefined
      && Pos.dist(s.pos.get, c.pos.get) <= s.portee
      && s.cooldown == 0) {
      c.soigner(s.soin)
      s.cooldown = nC
    }
  }

  def soinTourPrincipaleAvecDegats(
    e: Endommageable,
    nC: Int
  ) = {
    if (e.carte.tP.pv != e.carte.tP.pvMax) {
      soin(e, e.carte.tP, nC)
    }
  }


  /* AOE */

  def soinAOEPos(
    e: Endommageable,
    pos: (Double, Double),
    l: List[Endommageable],
    nC: Int
  ) = {
    if (SCible.aPortee(e, pos, e.portee)) {
      l.filter(t => SCible.aPortee(t, pos, e.rayon)).
        foreach(_.soigner(e.soin))
      e.cooldown = nC
    }
  }

  def soinAOEPlusFaibleAvecDegats(
    e: Endommageable,
    l: List[Endommageable],
    nC: Int
  ) = {
    if (e.pos.isDefined) {
      SCible.plusFaibleAvecDegats(
        SCible.filterAPortee(l, e.pos.get, e.portee),
      ) match {
        case None => ()
        case Some(c:Endommageable) => soinAOEPos(e, c.pos.get, l, nC)
      }
    }
  }



}
