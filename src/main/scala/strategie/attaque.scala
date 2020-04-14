/** TowerDefProj
  * attaque.scala
  */


package strategie
import jeu._
import ennemis._
import tours._


object SAttaque {

  /* STRATEGIES */

  def attaquePlusProche(
    e: Endommageable,
    l: List[Endommageable],
    nC: Int // nouveau cooldown après attaque
  ) = {
    if (e.pos.isDefined) {
      SCible.plusProche(
        SCible.filterAPortee(l, e.pos.get, portee),
        e.pos.get
      ) match {
        case None => ()
        case Some(c) => {
          c.degats(deg)
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
      


}
