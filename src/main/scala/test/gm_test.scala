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
        OManche.spawnSomewhere(this, new Fourmi, (5,7))
        OManche.spawnSomewhere(this, new Fourmi, (5,7))
        OManche.spawnSomewhere(this, new Fourmi, (12,2))
        OManche.spawnSomewhere(this, new Fourmi, (9,7))
      case 6 =>
        OManche.spawnSomewhere(this, new Fourmi, (0,4))
        OManche.spawnSomewhere(this, new Fourmi, (5,7))
        OManche.spawnSomewhere(this, new Fourmi, (12,2))
        OManche.spawnSomewhere(this, new Fourmi, (9,7))
        OManche.spawnSomewhere(this, new Fourmi, (9,7))
      case 10 =>
        OManche.spawnSomewhere(this, new Fourmi, (0,4))
        OManche.spawnSomewhere(this, new Fourmi, (5,7))
        OManche.spawnSomewhere(this, new Fourmi, (5,7))
        OManche.spawnSomewhere(this, new Fourmi, (12,2))
      case 15 =>
        OManche.spawnSomewhere(this, new Fourmi, (0,4))
        OManche.spawnSomewhere(this, new Fourmi, (5,7))
        OManche.spawnSomewhere(this, new Fourmi, (9,7))
      case _ => ()
    }
  }

  def condFin: Option[Boolean] =
    OManche.condFinClassique(this, 15)

  def actionFin: Unit =
    gM.partie.argent += 100
}


object Manche2
    extends Manche {

  var gM: GestionManches = GMTest

  def actionTick = {
    nbTick match {
      case 1 =>
        OManche.spawnSomewhere(this, new Fourmi, (0,4))
        OManche.spawnSomewhere(this, new Fourmi, (5,7))
        OManche.spawnSomewhere(this, new Racaillou, (0,4))
        OManche.spawnSomewhere(this, new Fourmi, (12,2))
        OManche.spawnSomewhere(this, new Fourmi, (9,7))
        OManche.spawnSomewhere(this, new Fourmi, (9,7))
      case 6 =>
        OManche.spawnSomewhere(this, new Fourmi, (0,4))
        OManche.spawnSomewhere(this, new Fourmi, (5,7))
        OManche.spawnSomewhere(this, new Fourmi, (12,2))
        OManche.spawnSomewhere(this, new Fourmi, (9,7))
        OManche.spawnSomewhere(this, new Racaillou, (9,7))
        OManche.spawnSomewhere(this, new Racaillou, (0,4))
      case 10 =>
        OManche.spawnSomewhere(this, new Fourmi, (0,4))
        OManche.spawnSomewhere(this, new Fourmi, (5,7))
        OManche.spawnSomewhere(this, new Racaillou, (0,4))
        OManche.spawnSomewhere(this, new Racaillou, (12,2))
        OManche.spawnSomewhere(this, new Racaillou, (5,7))
        OManche.spawnSomewhere(this, new Fourmi, (12,2))
      case 15 =>
        OManche.spawnSomewhere(this, new Fourmi, (0,4))
        OManche.spawnSomewhere(this, new Fourmi, (5,7))
        OManche.spawnSomewhere(this, new Racaillou, (12,2))
        OManche.spawnSomewhere(this, new Racaillou, (0,4))
        OManche.spawnSomewhere(this, new Fourmi, (9,7))
      case 40 =>
        OManche.spawnSomewhere(this, new Fourmi, (0,4))
        OManche.spawnSomewhere(this, new Fourmi, (0,4))
        OManche.spawnSomewhere(this, new Fourmi, (5,7))
        OManche.spawnSomewhere(this, new Fourmi, (5,7))
        OManche.spawnSomewhere(this, new Fourmi, (12,2))
        OManche.spawnSomewhere(this, new Fourmi, (9,7))
        OManche.spawnSomewhere(this, new Fourmi, (9,7))
      case _ => ()
    }
  }

  def condFin: Option[Boolean] =
    OManche.condFinClassique(this, 40)

  def actionFin: Unit =
    gM.partie.argent += 150
}



object Manche3
    extends Manche {

  var gM: GestionManches = GMTest

  def actionTick = {
    nbTick match {
      case 1 =>
        OManche.spawnSomewhere(this, new Kamikaze, (5,7))
        OManche.spawnSomewhere(this, new Racaillou, (0,4))
        OManche.spawnSomewhere(this, new Fourmi, (12,2))
        OManche.spawnSomewhere(this, new Kamikaze, (9,7))
        OManche.spawnSomewhere(this, new Fourmi, (9,7))
      case 6 =>
        OManche.spawnSomewhere(this, new Kamikaze, (5,7))
        OManche.spawnSomewhere(this, new Kamikaze, (9,7))
        OManche.spawnSomewhere(this, new Racaillou, (9,7))
        OManche.spawnSomewhere(this, new Racaillou, (0,4))
      case 10 =>
        OManche.spawnSomewhere(this, new Kamikaze, (0,4))
        OManche.spawnSomewhere(this, new Fourmi, (5,7))
        OManche.spawnSomewhere(this, new Soignant, (0,4))
        OManche.spawnSomewhere(this, new Racaillou, (12,2))
        OManche.spawnSomewhere(this, new Racaillou, (5,7))
        OManche.spawnSomewhere(this, new Kamikaze, (12,2))
      case 15 =>
        OManche.spawnSomewhere(this, new Fourmi, (0,4))
        OManche.spawnSomewhere(this, new Soignant, (12,2))
        OManche.spawnSomewhere(this, new Racaillou, (0,4))
        OManche.spawnSomewhere(this, new Kamikaze, (9,7))
      case 40 =>
        OManche.spawnSomewhere(this, new Fourmi, (0,4))
        OManche.spawnSomewhere(this, new Kamikaze, (5,7))
        OManche.spawnSomewhere(this, new Fourmi, (5,7))
        OManche.spawnSomewhere(this, new Kamikaze, (12,2))
        OManche.spawnSomewhere(this, new Fourmi, (9,7))
        OManche.spawnSomewhere(this, new Kamikaze, (9,7))
      case _ => ()
    }
  }

  def condFin: Option[Boolean] =
    OManche.condFinClassique(this, 15)

  def actionFin: Unit =
    gM.partie.argent += 250
}


object Manche4
    extends Manche {

  var gM: GestionManches = GMTest

  def actionTick = {
    nbTick match {
      case 1 =>
        OManche.spawnSomewhere(this, new Johnson, (5,7))
      case _ => ()
    }
  }

  def condFin: Option[Boolean] =
    OManche.condFinClassique(this, 1)

  def actionFin: Unit = ()
}


object GMTest
    extends GestionManches {

  /* REFERENCES */

  var partie: Partie = PartieTest
  var m: List[Manche] = List(Manche1, Manche2, Manche3, Manche4)


  /* ATTRIBUTS */

  var mEnCours: Option[Manche] = None

}
