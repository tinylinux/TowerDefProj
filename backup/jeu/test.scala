
/** PROJET PROG 2
  * test.scala
  * Fichier contenant des classes filles des classes à tester, constituant un exemple
  * de ce que le jeu pourrait être
  */

package jeu

import affichage._

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

  // On suppose que le fichier est un fichier correct
  // (éventuellement on pourrait implémenter une fonction qui vérifie que le fichier est correct)

  // mise en place de la tourPrincipale
  override val tourPrincipale = new TourPrincipale(this, 10)

  // lecture du fichier
  val filename = "test.txt"
  lectureCarteFichier(filename)

  // apparition des ennemis
  manchesSuivantes = List(
    new Manche(this) { apparitions = List(
      (new Ennemi(this.carte, 5),0),
      (new Ennemi(this.carte, 7),0),
      (new Ennemi(this.carte, 15), 10),
      (new Ennemi(this.carte, 12), 16),
      (new Ennemi(this.carte, 5), 9)
    ) },

    new Manche(this) { apparitions = List(
      (new Ennemi(this.carte, 8),0),
      (new Ennemi(this.carte, 9),3),
      (new Ennemi(this.carte, 15),8),
      (new Ennemi(this.carte, 30),15)
    ) }
  )

  // mise en place d'une tour
  spawnTour(new TourAttaque(this, 1, 10), (5,2))

/*
  override def guideEnnemi(deb : (Double,Double)) : (Double,Double) = {
    if (Carte.distance(deb,(5.5,1.5)) < 1.0)
      (7.5,1.5)
    else
      (5.5,1.5)
  }
*/
  override def spawnEnnemiPos() : (Double, Double) = (1.5, 1.5)

}
