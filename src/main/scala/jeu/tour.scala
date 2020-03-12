
/** PROJET PROG 2
  * tour.scala
  */
package jeu

import affichage._


import java.awt.image.BufferedImage


abstract class Tour(var carte : Carte, override val pvMax : Int)
    extends Endommageable(pvMax)
    with Tickable
    with HasSprite {

  var pos : Option[(Int,Int)] = None


  // extends Endommageable
  // rien à ajouter

  // extends Tickable
  def tick: Unit

  // extends HasSprite
  def sprite: BufferedImage =
    MethodesAffichage.chargerImage("tour.jpg")

  def atPosition(p: (Int,Int)): Boolean =
    pos.isDefined && pos.get == p

}


object TourAttaque extends TourInvent {
  val resizedSprite = MethodesAffichage.chargerImage("tour_attaque.jpg")

  def creerInstance = new TourAttaque(null, 5, 2.0)
}


class TourAttaque(var map: Carte, override val pvMax: Int, val portee: Double)
    extends Tour(map, pvMax) {
  override def tick: Unit = {
    // recherche des ennemis à portée

    val aPortee = carte.ennemis.filter(e =>
      e.pos.isDefined &&
        Carte.distance(e.pos.get, (this.pos.get._1:Double, this.pos.get._2:Double)) < portee
    )

    aPortee.foreach(e => e.infligerDegats(1))
  }
}



class TourPrincipale(var map : Carte, override val pvMax : Int)
    extends Tour(map, pvMax) {
  override def tick: Unit = () // la tour principale ne fait rien
}
