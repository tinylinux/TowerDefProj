/** TowerDefProj
  * manche.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


abstract class Manche {

  /* REFERENCES */

  var gM: GestionManches


  /* ATTRIBUTS */

  var nbTick: Int = 0


  /* METHODES */

  def tick: Unit =
    OGestionManches.tickM(this)
  
  def actionTick: Unit
  def condFin: Option[Boolean]

}
