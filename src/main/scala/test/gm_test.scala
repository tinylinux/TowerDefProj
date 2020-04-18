/** TowerDefProj
  * gestion_manches.scala
  */


/* PACKAGES */

package test
import jeu._
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


object Manche1
    extends Manche {

  var gM: GestionManches = GMTest

  def actionTick = {
    nbTick match {
      case 1 =>
        OManche.spawnSomewhere(this, new Fourmi, (0,4))
      case 10 =>
        OManche.spawnSomewhere(this, new Fourmi, (0,4))
        OManche.spawnSomewhere(this, new Racaillou, (0,4))
      case 15 =>
        OManche.spawnSomewhere(this, new Fourmi, (0,4))
      case _ => ()
    }
  }

  def condFin: Option[Boolean] =
    OManche.condFinClassique(this, 15)

  def actionFin: Unit =
    gM.partie.argent += 50
}


object GMTest
    extends GestionManches {

  /* REFERENCES */

  var partie: Partie = PartieTest
  var m: List[Manche] = List(Manche1)


  /* ATTRIBUTS */

  var mEnCours: Option[Manche] = None

}
