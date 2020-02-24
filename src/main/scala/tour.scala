
/** PROJET PROG 2
  * tour.scala
  */



class Tour(var carte : Carte, override val pvMax : Int)
    extends Endommageable(pvMax)
    with Tickable
    with HasSprite {

  var pos : Option[(Int,Int)] = None


  // extends Endommageable
  // rien à ajouter

  // extends Tickable
  def tick : Unit = () // par défaut une tour ne fait rien..

  // extends HasSprite
  def sprite : Unit = ()

}



class TourPrincipale(var map : Carte, override val pvMax : Int)
    extends Tour(map, pvMax)
