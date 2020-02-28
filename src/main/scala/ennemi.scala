
/** PROJET PROG 2
  * ennemi.scala
  */


import java.awt.image.BufferedImage


class Ennemi(var carte : Carte, override val pvMax : Int)
    extends Endommageable(pvMax)
    with HasSprite
    with Tickable {


  // None ou Some(x,y)
  var pos : Option[(Double,Double)] = None

  // vitesse en distance/tick
  var vitesse : Double = 0.1

  // extends Tickable
  def tick : Unit = {
    // si l'Ennemi n'est pas positionné sur la carte ou qu'il est mort, ne rien faire
    if (pos.isDefined && pv > 0) {
      val deb : (Double, Double) = pos.get

      // *************** DEPLACEMENT ***************
      // demande de la position à atteindre à la carte
      val cib = this.carte.guideEnnemi(deb)

      // déplacement vers cette position
      val dist = carte.distance((deb._1, deb._2), (cib._1, cib._2))
      val dx = vitesse * (cib._1 - deb._1) / dist
      val dy = vitesse * (cib._2 - deb._2) / dist
      pos = Some((deb._1 + dx, deb._2 + dy))

      // *************** ATTAQUE ******************

      // A FAIRE !! (osef pour l'instant)
    }
  }


  // extends HasSprite
  def sprite : BufferedImage =
    MethodesAffichage.chargerImage("monstre.png")

  // extends Endommageable
  // rien à rajouter
}
