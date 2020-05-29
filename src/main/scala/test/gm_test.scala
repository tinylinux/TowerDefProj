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

/*
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
 */

  val s1 = (0,4)
  val s2 = (5,7)
  val s3 = (9,7)
  val s4 = (12,2)

  var fin = 15
  var sTick:List[(TypeEndommageable, Posmt, Int)] = List(
    (TypeFourmi, Posmt_Int(s1), 1),
    (TypeFourmi, Posmt_Int(s2), 1),
    (TypeFourmi, Posmt_Int(s2), 1),
    (TypeFourmi, Posmt_Int(s3), 1),
    (TypeFourmi, Posmt_Int(s4), 1),
    (TypeFourmi, Posmt_Int(s1), 6),
    (TypeFourmi, Posmt_Int(s1), 6),
    (TypeFourmi, Posmt_Int(s2), 6),
    (TypeFourmi, Posmt_Int(s3), 6),
    (TypeFourmi, Posmt_Int(s4), 6),
    (TypeFourmi, Posmt_Int(s1), 10),
    (TypeFourmi, Posmt_Int(s2), 10),
    (TypeFourmi, Posmt_Int(s3), 10),
    (TypeFourmi, Posmt_Int(s4), 10),
    (TypeFourmi, Posmt_Int(s1), 15),
    (TypeFourmi, Posmt_Int(s3), 15),
    (TypeFourmi, Posmt_Int(s4), 15)
  )
  var sPer:List[(TypeEndommageable, Posmt, Int)] = Nil
  var sAleat:List[(TypeEndommageable, Posmt, Double)] = Nil


  def actionFin: Unit =
    gM.partie.argent += 100
}


object Manche2
    extends Manche {

  var gM: GestionManches = GMTest

  val s1 = (0,4)
  val s2 = (5,7)
  val s3 = (9,7)
  val s4 = (12,2)

  var fin = 40
  var sTick:List[(TypeEndommageable, Posmt, Int)] = List(
    (TypeFourmi, Posmt_Int(s1), 1),
    (TypeFourmi, Posmt_Int(s2), 1),
    (TypeRacaillou, Posmt_Int(s1), 1),
    (TypeFourmi, Posmt_Int(s4), 1),
    (TypeFourmi, Posmt_Int(s3), 1),
    (TypeFourmi, Posmt_Int(s3), 1),

    (TypeFourmi, Posmt_Int(s1), 6),
    (TypeFourmi, Posmt_Int(s2), 6),
    (TypeFourmi, Posmt_Int(s4), 6),
    (TypeFourmi, Posmt_Int(s3), 6),
    (TypeRacaillou, Posmt_Int(s2), 6),
    (TypeRacaillou, Posmt_Int(s4), 6),

    (TypeFourmi, Posmt_Int(s2), 10),
    (TypeRacaillou, Posmt_Int(s1), 10),
    (TypeRacaillou, Posmt_Int(s4), 10),
    (TypeRacaillou, Posmt_Int(s2), 10),
    (TypeFourmi, Posmt_Int(s4), 10),

    (TypeFourmi, Posmt_Int(s1), 15),
    (TypeFourmi, Posmt_Int(s2), 15),
    (TypeFourmi, Posmt_Int(s3), 15),
    (TypeRacaillou, Posmt_Int(s4), 15),
    (TypeRacaillou, Posmt_Int(s1), 15),

    (TypeFourmi, Posmt_Int(s1), 40),
    (TypeFourmi, Posmt_Int(s1), 40),
    (TypeFourmi, Posmt_Int(s2), 40),
    (TypeFourmi, Posmt_Int(s2), 40),
    (TypeFourmi, Posmt_Int(s3), 40),
    (TypeFourmi, Posmt_Int(s3), 40),
    (TypeFourmi, Posmt_Int(s4), 40)
  )

  var sPer:List[(TypeEndommageable, Posmt, Int)] = Nil
  var sAleat:List[(TypeEndommageable, Posmt, Double)] = Nil

  def actionFin: Unit =
    gM.partie.argent += 150
}


/*
object Manche3
    extends Manche {

  var gM: GestionManches = GMTest

  override def actionTick = {
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

  var fin = 40
  var sTick:List[(TypeEndommageable, Posmt, Int)] = Nil
  var sPer:List[(TypeEndommageable, Posmt, Int)] = Nil
  var sAleat:List[(TypeEndommageable, Posmt, Double)] = Nil

  def actionFin: Unit =
    gM.partie.argent += 250
}


object Manche4
    extends Manche {

  var gM: GestionManches = GMTest

  override def actionTick = {
    nbTick match {
      case 1 =>
        OManche.spawnSomewhere(this, new Johnson, (5,7))
      case _ => ()
    }
  }

  var fin = 1
  var sTick:List[(TypeEndommageable, Posmt, Int)] = Nil
  var sPer:List[(TypeEndommageable, Posmt, Int)] = Nil
  var sAleat:List[(TypeEndommageable, Posmt, Double)] = Nil

  def actionFin: Unit = ()
}

 */


object GMTest
    extends GestionManches {

  /* REFERENCES */

  var partie: Partie = PartieTest
  var m: List[Manche] = List(Manche1, Manche2) /* , Manche3, Manche4) */


  /* ATTRIBUTS */

  var mEnCours: Option[Manche] = None

}
