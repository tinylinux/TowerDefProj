
/** PROJET PROG 2
  * carte.scala
  */

package jeu

import affichage._


import scala.math.sqrt
import java.awt.image.BufferedImage

object Carte {

  /** Renvoie la distance entre a et b */
  def distance(a : (Double, Double), b : (Double, Double)) : Double = {
    val dx = a._1 - b._1
    val dy = a._2 - b._2
    sqrt(dx*dx + dy*dy)
  }

}

class Carte extends Tickable {

  /* Liste des ennemis présents sur la carte */
  var ennemis : List[Ennemi] = List()

  /* Liste des tours présentes sur la carte */
  var tours : List[Tour] = List()

  /* Dimensions de la carte */
  /* La carte est un rectangle,
   * x désigne les abscisses et croit vers la droite de l'écran
   * y désigne les ordonnées et croit vers le bas de l'écran
   * x et y vont de 0 (inclu) à max_x et max_y (inclus)
   */
  var maxX : Int = 0
  var maxY : Int = 0

  /* Liste des cases qui composent la carte */
  /* Chaque Array[Case] désigne une ligne de la carte (c'est-à-dire pour
   * une valeur de y constante), cases est donc un tableau contenant chaque
   * ligne.
   */
  var cases : Array[Array[Case]] = Array()



  // extends Tickable
  def tick() : Unit = {
    // SUPPRESSION DES ENNEMIS ET TOURS MORTES
    ennemis = (new Endommageable(1)).supprimerMorts(ennemis)
    tours = (new Endommageable(1)).supprimerMorts(tours)

    // action des tours et ennemis
    for (e <- ennemis)
      e.tick()
    for (t <- tours)
      t.tick()
  }

  /** Renvoie la position vers laquelle devrait se diriger un ennemi situé à
    * la position deb pour atteindre la tour principale
    */
  def guideEnnemi(deb : (Double,Double)) : (Double,Double) = (0.0, 0.0)


  /** Fait apparaître un ennemi sur la carte */
  def spawnEnnemi(e : Ennemi) : Unit = {
    // la carte connaît l'ennemi
    ennemis = e :: ennemis

    // l'ennemi connaît la carte
    e.carte = this

    // positionnement de l'ennemi sur la carte
    e.pos = Some(spawnEnnemiPos())
  }

  /** Renvoie une position d'apparition pour un nouvel ennemi */
  def spawnEnnemiPos() : (Double, Double) = (0.0, 0.0)

  def spawnTour(t : Tour, p : (Int, Int)) : Unit = {
    // la carte connaît la tour
    tours = t :: tours

    // la tour connaît la carte
    t.carte = this

    // positionnement de la tour sur la carte
    t.pos = Some(p)
  }
}


abstract class Case {
  def accesEnnemi() : Boolean = true
  def accesTour() : Boolean = true

  // extends HasSprite
  def sprite : BufferedImage
}


case class Sol() extends Case {
  override def accesEnnemi() : Boolean = true
  override def accesTour() : Boolean = false

  override def sprite: BufferedImage = MethodesAffichage.chargerImage("herbe.jpg")
}

case class Mur() extends Case {
  override def accesEnnemi() : Boolean = false
  override def accesTour() : Boolean = true

  override def sprite: BufferedImage = MethodesAffichage.chargerImage("mur.jpg")
}


