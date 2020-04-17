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


object OManche {

  /* METHODES */

  def condFinClassique(
    m: Manche
  ) = {
    if (m.gM.partie.carte.tP == null)
      Some(false)
    else if (m.gM.partie.carte.ennemis.isEmpty)
      Some(true)
    else
      None
  }


}
