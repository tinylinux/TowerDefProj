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



object Manche3
    extends Manche {

  var gM: GestionManches = GMTest

  val s1 = (0,4)
  val s2 = (5,7)
  val s3 = (9,7)
  val s4 = (12,2)

  var fin = 40
  var sTick:List[(TypeEndommageable, Posmt, Int)] = List(
    (TypeFourmi, Posmt_Int(s1), 1),
    (TypeRacaillou, Posmt_Int(s1), 1),
    (TypeKamikaze, Posmt_Int(s4), 1),
    (TypeKamikaze, Posmt_Int(s3), 1),
    (TypeFourmi, Posmt_Int(s3), 1),

    (TypeKamikaze, Posmt_Int(s2), 6),
    (TypeFourmi, Posmt_Int(s4), 6),
    (TypeFourmi, Posmt_Int(s3), 6),
    (TypeRacaillou, Posmt_Int(s2), 6),
    (TypeRacaillou, Posmt_Int(s4), 6),

    (TypeFourmi, Posmt_Int(s2), 10),
    (TypeRacaillou, Posmt_Int(s1), 10),
    (TypeKamikaze, Posmt_Int(s4), 10),
    (TypeRacaillou, Posmt_Int(s2), 10),
    (TypeFourmi, Posmt_Int(s4), 10),

    (TypeFourmi, Posmt_Int(s1), 15),
    (TypeKamikaze, Posmt_Int(s3), 15),
    (TypeRacaillou, Posmt_Int(s4), 15),
    (TypeRacaillou, Posmt_Int(s1), 15),

    (TypeKamikaze, Posmt_Int(s1), 40),
    (TypeFourmi, Posmt_Int(s2), 40),
    (TypeRacaillou, Posmt_Int(s2), 40),
    (TypeFourmi, Posmt_Int(s4), 40)
  )

  var sPer:List[(TypeEndommageable, Posmt, Int)] = List(
    (TypeFourmi, Posmt_Int(s1), 8)
  )
  var sAleat:List[(TypeEndommageable, Posmt, Double)] = Nil

  def actionFin: Unit =
    gM.partie.argent += 250
}



object Manche4
    extends Manche {

  var gM: GestionManches = GMTest

  val s1 = (0,4)
  val s2 = (5,7)
  val s3 = (9,7)
  val s4 = (12,2)

  var fin = 1
  var sTick:List[(TypeEndommageable, Posmt, Int)] = List(
    (TypeJohnson, Posmt_Int(s2), 1)
  )
  var sPer:List[(TypeEndommageable, Posmt, Int)] = Nil
  var sAleat:List[(TypeEndommageable, Posmt, Double)] = Nil

  def actionFin: Unit = ()
}



object GMTest
    extends GestionManches {

  /* REFERENCES */

  var partie: Partie = PartieTest
  var m: List[Manche] = List(Manche1, Manche2) /* , Manche3, Manche4) */


  /* ATTRIBUTS */

  var mEnCours: Option[Manche] = None

}
