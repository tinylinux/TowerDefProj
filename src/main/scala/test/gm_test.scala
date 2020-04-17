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
        gM.partie.carte.spawnE(new Fourmi, (1.5, 1.5))
      case 10 =>
        gM.partie.carte.spawnE(new Fourmi, (1.2, 1.3))
        gM.partie.carte.spawnE(new Racaillou, (1.3, 1.9))
      case 15 =>
        gM.partie.carte.spawnE(new Fourmi, (1.2, 1.6))
    }
  }

  def condFin: Option[Boolean] =
    OManche.condFinClassique(this)
}


object GMTest
    extends GestionManches {

  /* REFERENCES */

  var partie: Partie = PartieTest
  var m: List[Manche] = List(Manche1)


  /* ATTRIBUTS */

  var mEnCours: Option[Manche] = None

}
