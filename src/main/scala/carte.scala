
/** PROJET PROG 2
  * carte.scala
  */

import scala.math.sqrt


class Carte extends Tickable {

  /* Liste des ennemis présents sur la carte */
  var ennemis : List[Ennemi] = Nil

  /* Liste des tours présentes sur la carte */
  var tours : List[Tours] = Nil

  /* Dimensions de la carte */
  /* La carte est un rectangle,
   * x désigne les abscisses et croit vers la gauche de l'écran
   * y désigne les ordonnées et croit vers le haut de l'écran
   * x et y vont de 0 (inclu) à max_x et max_y (inclus)
   */
  var max_x : Int
  var max_y : Int

  /* Liste des cases qui composent la carte */
  /* Chaque Array[Case] désigne une ligne de la carte (c'est-à-dire pour
   * une valeur de y constante), cases est donc un tableau contenant chaque
   * ligne.
   */
  var cases : Array[Array[Case]]



  // extends Tickable
  def tick() : Unit = {
    // SUPPRESSION DES ENNEMIS ET TOURS MORTES
    ennemis = Endommageable.supprimerMorts(ennemis)
    tours = Endommageable.supprimerMorts(tours)

    // action des tours et ennemis
    for (e <- ennemis)
      e.tick()
    for (t <- tours)
      t.tick()
  }

  /** Renvoie la position vers laquelle devrait se diriger un ennemi situé à
    * la position deb pour atteindre la tour principale
    */
  def guideEnnemi(deb : (Float,Float)) : (Float,Float)

  /** Renvoie la distance entre a et b */
  def distance(a : (Float * Float), b : (Float, Float)) : Float =
    sqrt((a._1 - b._1)^2 + (a._2 - b._2)^2)

  /** Fait apparaître un ennemi sur la carte */
  def spawnEnnemi(e : Ennemi) : Unit = {
    // la carte connaît l'ennemi
    ennemis.appended(e)

    // l'ennemi connaît la carte
    e.carte = self

    // positionnement de l'ennemi sur la carte
    e.pos = spawnEnnemiPos()
  }

  /** Renvoie une position d'apparition pour un nouvel ennemi */
  def spawnEnnemiPos() : (Float, Float)

  def spawnTour(t : Tour, p : (Float, Float)) : Unit = {
    // la carte connaît la tour
    ennemis.appended(e)

    // la tour connaît la carte
    e.carte = self

    // positionnement de la tour sur la carte
    e.pos = p
  }
}


class Case {
  def accesEnnemi() : Boolean = true
  def accesTour() : Boolean = true

  // extends HasSprite
  def sprite : Unit = ()
}


case class Sol extends Case {
  override def accesEnnemi() : Boolean = true
  override def accesTour() : Boolean = false
}

case class Mur extends Case {
  override def accesEnnemi() : Boolean = false
  override def accesTour() : Boolean = true
}


