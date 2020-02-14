
/** PROJET PROG 2
  * tour.scala
  */



class Tour extends Tickable {

  var pos : Option[(Int,Int)]


  // extends Tickable
  def tick : Unit = () // par défaut une tour ne fait rien..

  // extends HasSprite
  def sprite : Unit = ()
  def reloadSprite : Boolean = false
}



class TourPrincipale extends Tour
