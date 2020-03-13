
/** PROJET PROG 2
  * test.scala
  * Fichier contenant des classes filles des classes à tester, constituant un exemple
  * de ce que le jeu pourrait être
  */

package jeu

import affichage._



class CarteTest extends Carte {

  // définition de la carte
  /*
   *   0 1 2 3 4 5 6 7 8 9
   * 0 X X X X X X X X X X
   * 1 X S O O O O O O P X
   * 2 X X X X X X X X X X

   * X : mur
   * S : spawn
   * O : ouvert
   * T : tour principale

   */

  maxX = 9
  maxY = 2


  cases = Array(
    Array(Mur(), Mur(), Mur(), Mur(), Mur(), Mur(), Mur(), Mur(), Mur(), Mur()),
    Array(Mur(), Sol(), Sol(), Sol(), Sol(), Sol(), Sol(), Sol(), Sol(), Mur()),
    Array(Mur(), Mur(), Mur(), Mur(), Mur(), Mur(), Mur(), Mur(), Mur(), Mur())
  )


  // apparition des ennemis
  spawnEnnemi(new Ennemi(this, 5))
  spawnEnnemi(new Ennemi(this, 7))

  // mise en place de la tourPrincipale
  override val tourPrincipale = new TourPrincipale(this, 10)
  spawnTour(tourPrincipale, (8,1))

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
