/** TowerDefProj
  * cible.scala
  */


package strategie
import jeu._
import ennemis._
import tours._

import scala.math.ceil


object SCible {

  def optMinBy[A, B](
    l: List[A],
    f: (A) -> B,
  ) = {n
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
  ) = SCible.optMinBy(
    surCarte(l),
    e => Pos.dist(e.pos.get, pos))


  def plusFaible[A <: Endommageable](
    l: List[A]
  ) = optMinBy(surCarte(l), e => e.pv)


  def plusFaibleAvecDegats[A <: Endommageable](
    l: List[A]
  ) = optMinBy(
    surCarte(l).filter(e => e.pv != e.pvMax),
    e => e.pv)

  /* Renvoie l'endommageable avec le plus gros ratio atq_cible/nb de coups pour l'éliminer.
   * Permet de sélectionner la cible la plus dangereuse, à éliminer rapidement.
   */
  def meilleurRatio[A <: Endommageable](
    l: List[A],
    dpc: Int // dégâts par coup
  ) = optMinBy(surCarte(l),
    e => ceil((e.pv:Double)/dpc) / e.deg)
    


  /* METHODES SUR ENDOMMAGEABLES */

  def aPortee(
    e: Endommageable,
    pos: (Double, Double),
    portee: Double
  ) = e.pos.isDefined && Pos.dist(e.pos.get, pos) <= portee



}
