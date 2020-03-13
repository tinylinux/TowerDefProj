
/** PROJET PROG 2
  * test.scala
  * Fichier contenant des classes filles des classes à tester, constituant un exemple
  * de ce que le jeu pourrait être
  */

package jeu

import affichage._
import scala.io.Source

class CarteTest extends Carte {

  // définition de la carte
  /**
   *   0 1 2 3 4 5 6 7 8 9
   * 0 X X X X X X X X X X
   * 1 X S O O O O O O P X
   * 2 X X X X X X X X X X

   * X : mur
   * S : spawn
   * O : ouvert
   * T : tour principale

   */

   def generateElements(i:Char):Case = {
     if (i == 'X')
     {
       new Mur()
     }
     else
     {
       new Sol()
     }
   }

  val filename = "src/main/resources/maps/test.txt"
  val filelines = Source.fromFile(filename).getLines.toArray

  // On suppose que le fichier est un fichier correct
  // (éventuellement on pourrait implémenter une fonction qui vérifie que le fichier est correct)

  maxX = filelines(0).length - 1
  maxY = filelines.length - 1

  cases = Array.ofDim[Case](maxY+1, maxX+1)

  // mise en place de la tourPrincipale
  override val tourPrincipale = new TourPrincipale(this, 10)

  for (l <- filelines.indices) {
    for (c <- filelines(l).indices) {
      // Création de la case
      cases(l)(c) = generateElements(filelines(l)(c));

      // Création de la Tour Principale
      if (filelines(l)(c) == 'P')
      {
        spawnTour(tourPrincipale, (c,l))
      }
    }
  }

  /** cases = Array(
    Array(Mur(), Mur(), Mur(), Mur(), Mur(), Mur(), Mur(), Mur(), Mur(), Mur()),
    Array(Mur(), Sol(), Sol(), Sol(), Sol(), Sol(), Sol(), Sol(), Sol(), Mur()),
    Array(Mur(), Mur(), Mur(), Mur(), Mur(), Mur(), Mur(), Mur(), Mur(), Mur())
  ) */


  // apparition des ennemis
  spawnEnnemi(new Ennemi(this, 5))
  spawnEnnemi(new Ennemi(this, 7))
  spawnEnnemi(new Ennemi(this, 10))


  // mise en place d'une tour
  spawnTour(new TourAttaque(this, 1, 10), (5,2))

  override def guideEnnemi(deb : (Double,Double)) : (Double,Double) = {
    if (Carte.distance(deb,(5.5,1.5)) < 1.0)
      (7.5,1.5)
    else
      (5.5,1.5)
  }

  override def spawnEnnemiPos() : (Double, Double) = (1.5, 1.5)

}
