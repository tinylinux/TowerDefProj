
/** PROJET PROG 2
  * tour.scala
  */


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
