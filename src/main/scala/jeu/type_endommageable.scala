/** TowerDefProj
  * type_endommageable.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._

import java.awt.image.BufferedImage


abstract class TypeEndommageable
  [A <: Endommageable] {

  /* REFERENCES */




  /* ATTRIBUTS */

  var imgMag: BufferedImage
  var imgGrille: BufferedImage



  /* METHODES */

  def instance: A

}
