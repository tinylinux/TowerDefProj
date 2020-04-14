/** TowerDefProj
  * cible.scala
  */


package strategie
import jeu._
import ennemis._
import tours._


object SCible {

  def optMinBy[A, B](
    l: List[A],
    f: (A) -> B,
  ) = {
    try { Some(l.minBy(f)) }
    catch { case _ : Throwable => None }
  }

  /* METHODES SUR LISTES */

  def filterSurCarte[A <: Endommageable](
    l: List[A]
  ) = l.filter(e => e.pos.isDefined)


  def filterAPortee[A <: Endommageable](
    l: List[A],
    pos: (Double, Double),
    portee: Double
  ) = surCarte(l).filter(e =>
    Pos.dist(e.pos.get, pos) <= portee)


  def triProche[A <: Endommageable](
    l: List[A],
    pos: (Double, Double)
  ) = surCarte(l).sortBy(e =>
    Pos.dist(e.pos.get, pos))


  def plusProche[A <: Endommageable](
    l: List[A],
    pos: (Double, Double)
  ) = surCarte(l).optMinBy(e =>
    Pos.dist(e.pos.get, pos))


  /* METHODES SUR ENDOMMAGEABLES */

  def aPortee(
    e: Endommageable,
    pos: (Double, Double),
    portee: Double
  ) = e.pos.isDefined && Pos.dist(e.pos.get, pos) <= portee


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

}
