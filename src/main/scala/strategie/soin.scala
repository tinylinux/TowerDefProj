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
        SCible.filterAPortee(l, e.pos.get, portee),
        e.pos.get
      ) match {
        case None => ()
        case Some(c) => {
          c.soigner(soin)
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
      c.soigner(a.soin)
      a.cooldown = nC
    }
  }

  def soinTourPrincipaleAvecDegats(
    e: Endommageable,
    nC: Int
  ) = {
    if (e.carte.tP.pv != e.carte.tP.pvMax) {
      soigner(e, e.carte.tP, nC)
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
        SCible.filterAPortee(l, e.pos.get, portee),
        e.pos.get
      ) match {
n        case None => ()
        case Some(c) => soinAOEPos(e, c.pos.get, l, nC)
      }
    }
  }



}
