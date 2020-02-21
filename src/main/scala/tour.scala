
/** PROJET PROG 2
  * tour.scala
  */



class Tour extends Endommageable with Tickable with HasSprite {

  var pos : Option[(Int,Int)]


  // extends Endommageable
  // rien à ajouter

  // extends Tickable
  def tick : Unit = () // par défaut une tour ne fait rien..

  // extends HasSprite
  def sprite : Unit = ()

}



class TourPrincipale extends Tour
