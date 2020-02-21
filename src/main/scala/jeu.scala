
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

  def infligerDegats(d : Int) : Int {
    val pvInit = pv
    if (d >= 0)
      pv -= d
    if (pv < 0)
      pv = 0
    return pv-pvInit
  }

  def supprimerMorts(l : Iterable[Endommageable]) = {
    val maj_l = Nil
    for (e <- l) {
      if (e.pv != 0) {
        maj_l.appended(e)
      }
    }
    maj_l
  }
}
