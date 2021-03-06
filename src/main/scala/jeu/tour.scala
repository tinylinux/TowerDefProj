/** TowerDefProj
  * tour.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


abstract class Tour
    extends Endommageable {

  /* METHODES */

  def despawn: Unit =
    if (carte != null)
      carte.despawnT(this)

}
