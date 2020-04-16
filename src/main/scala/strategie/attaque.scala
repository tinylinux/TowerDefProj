/** TowerDefProj
  * attaque.scala
  */


package strategie
import jeu._
import ennemis._
import tours._


object SAttaque {

  /* STRATEGIES */

  /* ATTAQUES INDIVIDUELLES */

  def attaquePlusProche(
    e: Endommageable,
    l: List[Endommageable],
    nC: Int // nouveau cooldown après attaque
  ) = {
    if (e.pos.isDefined) {
      SCible.plusProche(
        SCible.filterAPortee(l, e.pos.get, e.portee),
        e.pos.get
      ) match {
        case None => ()
        case Some(c:Endommageable) => {
          c.degats(e.deg)
          e.cooldown = nC
        }
      }
    }
  }

  def attaque(
    a: Endommageable,
    c: Endommageable,
    nC: Int
  ) = {
    if (c.pos.isDefined && a.pos.isDefined
      && Pos.dist(a.pos.get, c.pos.get) <= a.portee
      && a.cooldown == 0) {
      c.degats(a.deg)
      a.cooldown = nC
    }
  }

  def attaqueTourPrincipale(
    e: Endommageable,
    nC: Int
  ) = attaque(e, e.carte.tP, nC)


  def attaqueMeilleurRatio(
    e: Endommageable,
    l: List[Endommageable],
    nC: Int
  ) = {
    if (e.pos.isDefined) {
      SCible.meilleurRatio(
        SCible.filterAPortee(
          l, e.pos.get, e.portee),
        e.deg ) match {
        case None => ()
        case Some(c:Endommageable) => {
          c.degats(e.deg)
          e.cooldown = nC
        } } }
  }


  /* AOE */

  def attaqueAOEPos(
    e: Endommageable,
    pos: (Double, Double),
    l: List[Endommageable],
    nC: Int
  ) = {
    if (SCible.aPortee(e, pos, e.portee)) {
      l.filter(t => SCible.aPortee(t, pos, e.rayon)).
        foreach(_.degats(e.deg))
      e.cooldown = nC
    }
  }

  def attaqueAOEPlusProche(
    e: Endommageable,
    l: List[Endommageable],
    nC: Int // nouveau cooldown après attaque
  ) = {
    if (e.pos.isDefined) {
      SCible.plusProche(
        SCible.filterAPortee(l, e.pos.get, e.portee),
        e.pos.get
      ) match {
        case None => ()
        case Some(c:Endommageable) => attaqueAOEPos(e, c.pos.get, l, nC)
      }
    }
  }



}
