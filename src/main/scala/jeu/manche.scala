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

  var fin: Int
  var sTick: List[(TypeEndommageable, Posmt, Int)]
  var sPer: List[(TypeEndommageable, Posmt, Int)]
  var sAleat: List[(TypeEndommageable, Posmt, Double)]


  /* METHODES */

  def tick: Unit =
    OGestionManches.tickM(this)
  
  def actionTick: Unit =
    OManche.actionTickClassique(this)

  def actionFin: Unit
  def condFin: Option[Boolean] =
    OManche.condFinClassique(this)

}
