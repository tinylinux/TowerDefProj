
/** PROJET PROG 2
  * jeu.scala
  */


/* GESTION DU TEMPS */

trait Tickable {
  def tick() : Unit
}


/* ENTITÉ DESTRUCTIBLE */

class Endommageable(val pvMax : Int) {
  var pv : Int = pvMax

  def infligerDegats(d : Int) : Int = {
    val pvInit = pv
    if (d >= 0)
      pv -= d
    if (pv < 0)
      pv = 0
    return pv-pvInit
  }

  def supprimerMorts[A <: Endommageable](l : List[A]) = {
    l.filter(e => e.pv > 0)
  }
}
