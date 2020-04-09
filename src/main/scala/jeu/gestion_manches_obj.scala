/** TowerDefProj
  * gestion_manches_obj.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


object GestionManches {

  /* METHODES */

  def chargerM(
    gM: GestionManches
  ): Unit = {
    if (!gM.mEnCours.isDefined) { gM.m match {
      case [] => ()
      case e :: m2 => {
        gM.mEnCours = Some(e)
        gM.m = m2
      } } }
  }

  def tickM(
    m: Manche
  ): Unit = {
    m.nbTick += 1
    m.actionTick
  }
      

}
