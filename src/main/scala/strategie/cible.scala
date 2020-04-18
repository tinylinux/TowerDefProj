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
    f: (A) => B,
  )(
    implicit cmp: Ordering[B]
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
  ) = filterSurCarte(l).filter(e =>
    Pos.dist(e.pos.get, pos) <= portee)


  /* Renvoie les éléments de l sur lesquels un effet ayant les
   * caractéristiques en paramètre est applicable
   */
  def filterHasNoEffect(
    l: List[Endommageable],
    et: TypeEffet,
    benef: Boolean,
    prio: Int
  ) = filterSurCarte(l).filterNot(
    (e:Endommageable) => {
      e.effets.exists(
        (eff:Effet) => {
          (eff.typeE == et) && (
            (eff.prio > prio) || (
              (eff.prio == prio) && (eff.benef == benef)
            )
          )
        } ) } )


  def triProche[A <: Endommageable](
    l: List[A],
    pos: (Double, Double)
  ) = filterSurCarte(l).sortBy((e:Endommageable) => {
    Pos.dist(e.pos.get, pos) } )


  def plusProche[A <: Endommageable](
    l: List[A],
    pos: (Double, Double)
  ) = SCible.optMinBy(
    filterSurCarte(l),
    (e:Endommageable) => { Pos.dist(e.pos.get, pos) } )


  def plusFaible[A <: Endommageable](
    l: List[A]
  ) = optMinBy(filterSurCarte(l), (e:Endommageable) => e.pv)


  def plusFaibleAvecDegats[A <: Endommageable](
    l: List[A]
  ) = optMinBy(
    filterSurCarte(l).filter(e => e.pv != e.pvMax),
    (e:Endommageable) => e.pv)

  /* Renvoie l'endommageable avec le plus gros ratio atq_cible/nb de coups pour l'éliminer.
   * Permet de sélectionner la cible la plus dangereuse, à éliminer rapidement.
   */
  def meilleurRatio[A <: Endommageable](
    l: List[A],
    dpc: Int // dégâts par coup
  ) = optMinBy(filterSurCarte(l),
    (e:Endommageable) => ceil((e.pv:Double)/dpc) / e.deg)
    


  /* METHODES SUR ENDOMMAGEABLES */

  def aPortee(
    e: Endommageable,
    pos: (Double, Double),
    portee: Double
  ) = e.pos.isDefined && Pos.dist(e.pos.get, pos) <= portee



}
