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
  var maxX: Int = 13
  var maxY: Int = 8
  var tuiles: Array[Array[Tuile]] =

    Array(
      Array(Mur(),Mur(),Mur(),Mur(),Spawn(),Mur(),Mur(),Mur()),
      Array(Sol(),Sol(),Sol(),Sol(),Sol(),Sol(),Sol(),Mur()),
      Array(Sol(),Mur(),Mur(),Sol(),Mur(),Mur(),Sol(),Mur()),
      Array(Sol(),Sol(),Sol(),Sol(),Sol(),Sol(),Sol(),Mur()),
      Array(Mur(),Sol(),Sol(),Mur(),Mur(),Mur(),Sol(),Mur()),
      Array(Mur(),Sol(),Sol(),Sol(),Sol(),Mur(),Sol(),Spawn()),
      Array(Sol(),Sol(),Mur(),Mur(),Sol(),Sol(),Sol(),Mur()),
      Array(Mur(),Sol(),Sol(),Sol(),Sol(),Mur(),Sol(),Mur()),
      Array(Mur(),Sol(),Mur(),Mur(),Sol(),Mur(),Sol(),Mur()),
      Array(Mur(),Sol(),Sol(),Sol(),Sol(),Mur(),Sol(),Spawn()),
      Array(Mur(),Sol(),Mur(),Mur(),Sol(),Mur(),Sol(),Mur()),
      Array(Mur(),Sol(),Sol(),Sol(),Sol(),Sol(),Sol(),Mur()),
      Array(Mur(),Mur(),Spawn(),Mur(),Mur(),Mur(),Mur(),Mur()),
    )


  /*    Array(
      Array(XXXXKXXX
      Array(OOOOOOOX
      Array(OXXOXXOX
      Array(OOOOOOOX
      Array(XOOXXXOX
      Array(XOOOOXOK
      Array(OOXXOOOX
      Array(XOOOOXOX
      Array(XOXXOXOX
      Array(XOOOOXOK
      Array(XOXXOXOX
      Array(XOOOOOOX
      Array(XXKXXXXX
    )
 */


  /* Spawn tour principale */
  tP = new TourPrincipale
  if (tP != null)
    spawnT(tP, (6,0))


}
