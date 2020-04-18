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



object OManche {

  /* METHODES */

  def condFinClassique(
    m: Manche,
    tF: Int // tick à partir duquel tous les ennemis sont apparus
  ) = {
    if (m.gM.partie.carte.tP == null)
      Some(false)
    else if (m.gM.partie.carte.ennemis.isEmpty && m.nbTick >= tF)
      Some(true)
    else
      None
  }


  def spawnSomewhere(
    m: Manche,
    e: Ennemi,
    posI: (Int, Int)
  ) =
    m.gM.partie.carte.spawnE(
      e, Pos.sPos(
        Pos.iToPos(posI),
        (Random.nextDouble(), Random.nextDouble())
      )
    )


}
