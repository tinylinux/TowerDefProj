/** TowerDefProj
  * carte.scala
  */


/* PACKAGES */

package test
import jeu._
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


object CarteTest
    extends Carte {

  var partie: Partie = PartieTest

  var tP: Tour = null
  var tours: List[Tour] = Nil
  var ennemis: List[Ennemi] = Nil

  /* Tuiles */
  var maxX: Int = 8
  var maxY: Int = 5
  var tuiles: Array[Array[Tuile]] =
    Array(
      Array(Mur(), Mur(), Mur(), Mur(), Mur()),
      Array(Mur(), Spawn(), Sol(), Sol(), Mur()),
      Array(Mur(), Mur(), Mur(), Sol(), Mur()),
      Array(Mur(), Sol(), Sol(), Sol(), Mur()),
      Array(Mur(), Sol(), Mur(), Sol(), Mur()),
      Array(Mur(), Sol(), Mur(), Sol(), Mur()),
      Array(Mur(), Sol(), Sol(), Sol(), Mur()),
      Array(Mur(), Mur(), Mur(), Mur(), Mur())
    )



  /* Spawn tour principale */
  tP = new TourPrincipale
  if (tP != null)
    spawnT(tP, (6,1))


}
