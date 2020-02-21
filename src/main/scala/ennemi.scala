
/** PROJET PROG 2
  * ennemi.scala
  */


class Ennemi extends Endommageable with HasSprite with Tickable (val carte : Carte) {
  // None ou Some(x,y)
  var pos : Option[(Float,Float)]

  // vitesse en distance/tick
  var vitesse : Float = 0.1

  // la carte dans laquelle est situé l'ennemi
  var carte : Carte


  // extends Tickable
  def tick : Unit = {
    // si l'Ennemi n'est pas positionné sur la carte ou qu'il est mort, ne rien faire
    if (pos.isDefined && pv > 0) {
      val deb = pos.get()

      // *************** DEPLACEMENT ***************
      // demande de la position à atteindre à la carte
      val cib = self.carte.guideEnnemi(pos)

      // déplacement vers cette position
      val dist = Carte.distance((deb._1, deb._2), (cib._1, cib._2))
      val dx = vitesse * (cib._1 - deb._1) / dist
      val dy = vitesse * (cib._2 - deb._2) / dist
      pos = Some((deb._1 + dx, deb._2 + dy))

      // *************** ATTAQUE ******************

      // A FAIRE !! (osef pour l'instant)
    }
  }


  // extends HasSprite
  def sprite : Unit = ()

  // extends Endommageable
  // rien à rajouter
}
