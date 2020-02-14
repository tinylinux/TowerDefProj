
/** PROJET PROG 2
  * carte.scala
  */


class Carte {

  var ennemis : List[Ennemi]

  var tours : List[Tours]

  var max_x : Int
  var max_y : Int

  var cases : Array[Array[Case]]

  def guideEnnemi(deb : (Float,Float)) : (Float,Float) = {
    if (distance(deb,(5.0,5.0)) < 1.0)
      (7.0,7.0)
    else
      (5.0,5.0)
  }

  /** Renvoie la distance entre a et b */
  def distance(a : (Float * Float), b : (Float, Float)) : Float =
    ((a._1 - b._1)^2 + (a._2 - b._2)^2)
}


class Case {
  def accesEnnemi() : Boolean = true
  def accesTour() : Boolean = true

  // extends HasSprite
  def sprite : Unit = ()
  def reloadSprite : Boolean = false
}


class Sol extends Case {
  override def accesEnnemi() : Boolean = true
  override def accesTour() : Boolean = false
}

class Mur extends Case {
  override def accesEnnemi() : Boolean = false
  override def accesTour() : Boolean = true
}


