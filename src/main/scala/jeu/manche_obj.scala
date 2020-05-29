/** TowerDefProj
  * manche_obj.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._

import scala.util.Random

trait Posmt
case class Posmt_Int(p: (Int,Int)) extends Posmt
case class Posmt_Double(p: (Double,Double)) extends Posmt

object OManche {

  /* METHODES */

  def condFinClassique(
    m: Manche
  ) = {
    if (m.gM.partie.carte.tP == null)
      Some(false)
    else if (m.gM.partie.carte.ennemis.isEmpty && m.nbTick >= m.fin)
      Some(true)
    else
      None
  }



  def instanceEnn(
    t: TypeEndommageable
  ) = { t match {
    case TypeFourmi => new Fourmi
    case TypeJohnson => new Johnson
    case TypeKamikaze => new Kamikaze
    case TypeRacaillou => new Racaillou
    case TypeSoignant => new Soignant
  } }

  def spawnSomewhere(
    m: Manche,
    enn: Ennemi,
    p: (Int,Int)
  ) = {
    m.gM.partie.carte.spawnE(
      enn, Pos.sPos(
        Pos.iToPos(p),
        (Random.nextDouble(), Random.nextDouble())
      )
    )
  }



  def spawnEnn(
    m: Manche,
    enn: Ennemi,
    p: Posmt
  ) = { p match {
    case Posmt_Double(pos) =>
      m.gM.partie.carte.spawnE(
        enn, pos)
    case Posmt_Int(posI) =>
      m.gM.partie.carte.spawnE(
        enn, Pos.sPos(
          Pos.iToPos(posI),
          (Random.nextDouble(), Random.nextDouble())
        )
      )
  } }


  def actionTickClassique(
    m: Manche
  ) = {
    /* Apparition des ennemis */
    var flag = true
    while (flag) { m.sTick match {
      case Nil => flag = false
      case (t,e:Posmt,i) :: l => {
        if (m.nbTick >= i) {
          spawnEnn(m, instanceEnn(t), e)          
          m.sTick = l
        }
        else flag = false
      }
    } }

    var li = m.sPer
    while (!li.isEmpty) { li match {
      case (t,e:Posmt,i) :: li2 => {
        if (m.nbTick % i == 0) {
          spawnEnn(m, instanceEnn(t), e)
        }
        li = li2
      }
    } }

    var ld = m.sAleat
    while (!ld.isEmpty) { ld match {
      case (t,e:Posmt,d) :: ld2 => {
        if (Random.nextDouble() <= d)
          spawnEnn(m, instanceEnn(t), e)
        ld = ld2
      }
    } }
  }
}
