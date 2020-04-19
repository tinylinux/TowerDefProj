/** TowerDefProj
  * ennemi.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


abstract class Ennemi
    extends Endommageable {

  /* METHODES */

  def despawn: Unit =
    if (carte != null)
      carte.despawnE(this)

}
