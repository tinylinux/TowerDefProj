/** TowerDefProj
  * case.scala
  */


package jeu

import affichage._

import java.awt.image.BufferedImage


abstract class Case {
  def accesEnnemi : Boolean = true
  def accesTour : Boolean = true

  // extends HasSprite
  def sprite : BufferedImage
}


case class Sol() extends Case {
  override def accesEnnemi : Boolean = true
  override def accesTour : Boolean = false

  override def sprite: BufferedImage = MethodesAffichage.chargerImage("herbe.jpg")
}

case class Mur() extends Case {
  override def accesEnnemi : Boolean = false
  override def accesTour : Boolean = true

  override def sprite: BufferedImage = MethodesAffichage.chargerImage("mur.jpg")
}

case class Spawn() extends Case {
  override def accesEnnemi : Boolean = true
  override def accesTour : Boolean = false

  override def sprite: BufferedImage = MethodesAffichage.chargerImage("spawn.jpg")
}


