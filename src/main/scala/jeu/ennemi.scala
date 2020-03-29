
/** PROJET PROG 2
  * ennemi.scala
  */

package jeu

import affichage._


import java.awt.image.BufferedImage


class Ennemi(var carte : Carte, override val pvMax : Int)
    extends Endommageable
    with HasSprite
    with Tickable {

  // Argent obtenu par le joueur lorsque l'ennemi meurt
  val recompense: Int = 15

  // None ou Some(x,y)
  var pos : Option[(Double,Double)] = None

  var portee: Double = 1.0

  // vitesse en distance/tick
  var vitesse : Double = 0.1

  // extends Tickable
  def tick : Unit = {
    // si l'Ennemi n'est pas positionné sur la carte ou qu'il est mort, ne rien faire
    if (pos.isDefined && pv > 0 && carte.tourPrincipale.pos.isDefined) {
      val deb : (Double, Double) = pos.get

      val posTPInt = carte.tourPrincipale.pos.get
      val posTP: (Double, Double) =
        (posTPInt._1 + 0.5, posTPInt._2 + 0.5)

      val distTP = Carte.distance(deb, posTP)
      if (distTP <= portee) {

        // *************** ATTAQUE ******************
        carte.tourPrincipale.infliger(1)

      } else {

        // *************** DEPLACEMENT ***************
        // demande de la position à atteindre à la carte
        val cib = carte.guideEnnemi(deb)

        // déplacement vers cette position
        val dist = Carte.distance(deb, cib)
        val dx = vitesse * (cib._1 - deb._1) / dist
        val dy = vitesse * (cib._2 - deb._2) / dist
        pos = Some((deb._1 + dx, deb._2 + dy))

      }
    }
  }


  // extends HasSprite
  def sprite : BufferedImage =
    MethodesAffichage.chargerImage("monstre.png")

  // extends Endommageable
  // rien à rajouter
}
