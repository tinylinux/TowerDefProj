/** manche.scala
  * PROJET PROG 2
  */


package jeu

import affichage._



class Manche(val carte: Carte) extends Tickable {

  /** liste des ennemis à faire apparaitre, avec le nombre de ticks au bout duquel ils doivent apparaître */
  var apparitions: List[(Ennemi,Int)] = List()
  var nbTicks: Int = 0
  var initted: Boolean = false

  def tick = {
    nbTicks += 1
    var flag = true
    while (flag) {
      apparitions match {
        case (e,t) :: apparitions2 =>
          if (t <= nbTicks) {
            carte.spawnEnnemi(e)
            apparitions = apparitions2
          }
          else {
            flag = false
          }

        case Nil =>
          flag = false
      }
    }
  }

  // Initialisation des monstres apparaissant dès le début
  def init = {
    if (!initted) {
      apparitions = apparitions.sortBy[Int](t => t._2)
      nbTicks = -1
      tick
      initted = true
    }
  }

  def gagne: Option[Boolean] = {
    if (initted && carte.tourPrincipale.mort)
    {Some(false)}

    else if (initted && apparitions.isEmpty && carte.ennemis.isEmpty)
    {Some(true)}

    else {None}
  }
}
