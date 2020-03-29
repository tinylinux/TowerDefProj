
/** PROJET PROG 2
  * carte.scala
  */

package jeu

import affichage._


import scala.math.sqrt
import java.awt.image.BufferedImage
import scala.io.Source

object Carte {

  /** Renvoie la distance entre a et b */
  def distance(a : (Double, Double), b : (Double, Double)) : Double = {
    val dx = a._1 - b._1
    val dy = a._2 - b._2
    sqrt(dx*dx + dy*dy)
  }


  /** Renvoie une nouvelle instance d'une carte lue depuis un fichier */
  def lectureCarteFichier(filename: String) = new Carte { lectureCarteFichier(filename) }

}

class Carte extends Tickable {

  /* Argent possédé par le joueur */
  var argent: Int = 0

  /* Manche en cours de jeu, gérant l'apparition des ennemis */
  var manche: Manche = null

  def chargerManche: Boolean = {
    manchesSuivantes match {
      case m :: lm2 =>
        manche = m
        manchesSuivantes = lm2
        manche.init
        true
      case Nil =>
        manche = null
        false
    }
  }

  /* Manches suivantes */
  var manchesSuivantes: List[Manche] = List()

  /* Tour à défendre par la joueur */
  val tourPrincipale: TourPrincipale = null

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
    // apparition des nouveaux ennemis
    manche.tick

    // action des tours et ennemis
    for (e <- ennemis)
      e.tick()
    for (t <- tours)
      t.tick()

    // GAIN D'ARGENT POUR LES ENNEMIS VAINCUS
    ennemis.filter(e => e.mort).foreach(e => argent += e.recompense)

    // SUPPRESSION DES ENNEMIS ET TOURS MORTES
    ennemis = Endommageable.supprimerMorts(ennemis)
    tours = Endommageable.supprimerMorts(tours)
  }


  var chemins: Array[Array[(Int, Int)]] = null
  /** Renvoie la position vers laquelle devrait se diriger un ennemi situé à
    * la position deb pour atteindre la tour principale
    */
  def guideEnnemi(deb : (Double,Double)) : (Double,Double) = {
    // calcul des chemins menant à la tour principale
    if (chemins == null) {
      chemins = Pathfinding.parcoursEnLargeur(
        maxX, maxY, cases, tourPrincipale.pos.get)
    }

    val (debX, debY) = deb
    val (finX, finY) = chemins(debY.toInt)(debX.toInt)

    (finX + 0.5, finY + 0.5)
  }


  /** Fait apparaître un ennemi sur la carte */
  def spawnEnnemi(e : Ennemi) : Unit = {
print("SPAWN")
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

  def despawnTour(p: (Int,Int)): Unit =
    tours = tours.filter(t => !t.atPosition(p))



  /** Modifie les cases de la carte, en lisant le nouveau modèle de carte dans un fichier */
  def lectureCarteFichier(filename: String) = {
    // lecture des lignes du fichier
    val filelines = Source.fromFile("src/main/resources/maps/" + filename).getLines.toArray

    // On suppose que le fichier est un fichier correct
    // (éventuellement on pourrait implémenter une fonction qui vérifie que le fichier est correct)

    // dimensions
    maxX = filelines(0).length - 1
    maxY = filelines.length - 1

    // cases
    cases = Array.ofDim[Case](maxY+1, maxX+1)
    for (l <- filelines.indices) {
      for (c <- filelines(l).indices) {
        filelines(l)(c) match {
          case 'X' =>
            cases(l)(c) = Mur()
          case 'O' =>
            cases(l)(c) = Sol()
          case 'P' =>
            cases(l)(c) = Sol()
            spawnTour(tourPrincipale, (c,l))
          case 'S' =>
            cases(l)(c) = Spawn()
        }
      }
    }
  }


}


