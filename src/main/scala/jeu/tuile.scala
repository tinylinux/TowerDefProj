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

  var img: BufferedImage
  var accesE: Boolean
  var accesT: Boolean

}
