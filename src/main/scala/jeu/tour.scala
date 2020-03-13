
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

  val typeTour: TourInvent

  // extends Endommageable
  // rien à ajouter

  // extends Tickable
  def tick: Unit

  // extends HasSprite
  val img = MethodesAffichage.chargerImage("tour.jpg")
  override def sprite = img

  def atPosition(p: (Int,Int)): Boolean =
    pos.isDefined && pos.get == p

}


object TourAttaque extends TourInvent {
  val resizedSprite = MethodesAffichage.chargerImage("tour_attaque.jpg")
  override def creerInstance = new TourAttaque(null, 5, 2.0)

  override def prix = 10
}


class TourAttaque(var map: Carte, override val pvMax: Int, val portee: Double)
    extends Tour(map, pvMax) {

  override val typeTour = TourAttaque

  override def tick: Unit = {
    // recherche des ennemis à portée

    val aPortee = carte.ennemis.filter(e =>
      e.pos.isDefined &&
        Carte.distance(e.pos.get, (this.pos.get._1:Double, this.pos.get._2:Double)) < portee
    )

    aPortee.foreach(e => e.infligerDegats(1))
  }
}


object TourPrincipale extends TourInvent {
  val resizedSprite = MethodesAffichage.chargerImage("tour_attaque.jpg")
  override def creerInstance = new TourAttaque(null, 5, 2.0)

  override def prix = 0
}


class TourPrincipale(var map : Carte, override val pvMax : Int)
    extends Tour(map, pvMax) {
  override val typeTour = TourPrincipale

  override val img = MethodesAffichage.chargerImage("tour_principale.jpg")
  override def sprite = img

  override def tick: Unit = () // la tour principale ne fait rien
}
