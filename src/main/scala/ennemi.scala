

/** PROJET PROG 2
  * ennemi.scala
  */


class Ennemi extends HasSprite with Tickable (val carte : Carte) {
  // None ou Some(x,y)
  var pos : Option[(Float,Float)]

  var vitesse : Float = 0.1 // vitesse en distance/tick


  // extends Tickable
  def tick : Unit = {
    // si l'Ennemi n'est pas positionné sur la carte ou qu'il est mort, ne rien faire
    if (pos.isDefined && pv > 0) {
      val deb = pos.get()

      // *************** DEPLACEMENT ***************
      // demande de la position à atteindre à la carte
      val cib = self.carte.guideEnnemi(pos)

      // déplacement vers cette position
      val dist = ((cib._1 - deb._1)^2 + (cib._2 - deb._2)^2)
      val dx = vitesse * (cib._1 - deb._1) / dist
      val dy = vitesse * (cib._2 - deb._2) / dist
      pos = Some((deb._1 + dx, deb._2 + dy))

      // *************** ATTAQUE ******************

      // A FAIRE !!
    }
  }


  // extends HasSprite
  def sprite : Unit = ()
  def reloadSprite : Boolean = false
}
