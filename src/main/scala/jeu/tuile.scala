/** TowerDefProj
  * tuile.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._

import java.awt.image.BufferedImage


abstract class Tuile {

  /* ATTRIBUTS */

  var img: String
  var accesE: Boolean
  var accesT: Boolean

}


case class Mur() extends Tuile {
  var img: String = "tuiles/mur.jpg"
  var accesE: Boolean = false
  var accesT: Boolean = true
}


case class Sol() extends Tuile {
  var img: String = "tuiles/herbe.jpg"
  var accesE: Boolean = true
  var accesT: Boolean = true
}


case class Spawn() extends Tuile {
  var img: String = "tuiles/spawn.jpg"
  var accesE: Boolean = true
  var accesT: Boolean = false
}
