/* TowerDefProj
 * parser.scala
 * Ce fichier contient les définitions des différents terminaux, combinateurs et règles
 * utilisés pour parser les fichiers .tdp.
 */

package sauvegardes

import scala.util.parsing.combinator._



object Verif {

  case object ErreurFormat extends Exception

  def verif(j: ParserJeu.DJeu) = {
    val c = j._1 /* carte */
    val t = j._2 /* tours */
    val m = j._3 /* manches */
    val a = j._4 /* affichage */

    verifCarte(c)
    verifTours(t)
    verifManches(m)
    verifAffichage(a)
  }

  def verifCarte(c: List[DCarte]) = {
    var argent: Option[Int] = None
    /* x, y indiqués dans le fichier */
    var x:Option[Int] = None
    var y:Option[Int] = None
    /* x, y observés sur les tuiles */
    var dimT:Option[(Int,Int)] = None

    var l = c
    while (!l.isEmpty) {
      l match { case a :: l2 => {
        a match {
          case DCarte_MaxX(v) =>
            if (x.isDefined) throw ErreurFormat
            else x = Some(v)
          case DCarte_MaxY(v) =>
            if (y.isDefined) throw ErreurFormat
            else y = Some(v)
          case DCarte_Argent(v) =>
            if (argent.isDefined) throw ErreurFormat
            else argent = Some(v)
          case DCarte_Tuiles(t) =>
            if (dimT.isDefined) throw ErreurFormat
            else {
              var xT = 0
              var yT = 0
              var lt = t
              while (!lt.isEmpty) {
                lt match { case s :: lt2 => {
                  yT = yT + 1
                  if (!lt.filter( c => c == 'O' || c == 'X' || c == 'S' ).isEmpty)
                    throw ErreurFormat
                  else {
                    if (yT == 1)
                      xT = s.length
                    else if (s.length != xT)
                      throw ErreurFormat
                  }
                  lt = lt2
                } }
              }
              dimT = Some(xT,yT)
            }
        }
        l = l2
      } }
    }

    if (!x.isDefined || !y.isDefined || !dimT.isDefined || !argent.isDefined)
      throw ErreurFormat
    if (dimT.get._1 != x.get || dimT.get._2 != y.get)
      throw ErreurFormat
  }


  def verifTours(t: List[List[DTour]]) {
    if (t.isEmpty) throw ErreurFormat
    var l = t
    while (!l.isEmpty) {
      l match { case a :: l2 => {
        verifTour(a)
        l = l2
      } }
    }
  }


  def verifTour(t: List[DTour]) {
    var tour = false
    var x = false
    var y = false
    var pvMax = false
    var pv = false
    var cooldownAct = false
    var cooldown = false
    var deg = false
    var soin = false
    var portee = false
    var rayon = false

    var l = t
    while (!l.isEmpty) {
      l match { case a :: l2 => {
        a match {
          case DTour_Tour(_) => if (tour) throw ErreurFormat else tour = true
          case DTour_X(_) => if (x) throw ErreurFormat else x = true
          case DTour_Y(_) => if (y) throw ErreurFormat else y = true
          case DTour_PvMax(_) => if (pvMax) throw ErreurFormat else pvMax = true
          case DTour_Pv(_) => if (pv) throw ErreurFormat else pv = true
          case DTour_CooldownAct(_) => if (cooldownAct) throw ErreurFormat else cooldownAct = true
          case DTour_Cooldown(_) => if (cooldown) throw ErreurFormat else cooldown = true
          case DTour_Deg(_) => if (deg) throw ErreurFormat else deg = true
          case DTour_Soin(_) => if (soin) throw ErreurFormat else soin = true
          case DTour_Portee(_) => if (portee) throw ErreurFormat else portee = true
          case DTour_Rayon(_) => if (rayon) throw ErreurFormat else rayon = true
        }
        l = l2
      } }
    }

    if (!tour || !x || !y) throw ErreurFormat
  }


  def verifManches(m: List[List[DManche]]) = {
    if (m.isEmpty) throw ErreurFormat
    var l = m
    while (!l.isEmpty) {
      l match { case a :: l2 => {
        verifManche(a)
        l = l2
      } }
    }
  }

  def verifManche(m: List[DManche]) = {
    var fin = false

    var l = m
    while (!l.isEmpty) {
      if (fin) throw ErreurFormat
      l match { case a :: l2 => {
        a match {
          case DManche_Spawn(s) =>
            verifSpawn(s)
          case DManche_Fin(f) =>
            verifFin(f)
            fin = true
        }
        l = l2
      } }
    }
    if (!fin) throw ErreurFormat
  }

  def verifSpawn(s: List[DSpawn]) = {
    var ennemi = false
    var pos = false
    var x = false
    var y = false
    var tick = false
    var per = false
    var aleat = false

    var l = s
    while (!l.isEmpty) {
      l match { case a :: l2 => {
        a match {
          case DSpawn_Ennemi(_) => if (ennemi) throw ErreurFormat else ennemi = true
          case DSpawn_Pos(_) => if (pos) throw ErreurFormat else pos = true
          case DSpawn_X(_) => if (x) throw ErreurFormat else x = true
          case DSpawn_Y(_) => if (y) throw ErreurFormat else y = true
          case DSpawn_Tick(_) => if (tick) throw ErreurFormat else tick = true
          case DSpawn_Per(_) => if (per) throw ErreurFormat else per = true
          case DSpawn_Aleat(_) => if (aleat) throw ErreurFormat else aleat = true
        }
        l = l2
      } }
    }

    if (!ennemi) throw ErreurFormat
    if (x != y || x == pos) throw ErreurFormat
    if (!(tick && !per && !aleat) && !(!tick && per && !aleat) && !(!tick && !per && aleat)) throw ErreurFormat
  }

  def verifFin(f: List[DFin]) = {
    var timeout = false

    var l = f
    while (!l.isEmpty) {
      l match { case a :: l2 => {
        a match {
          case DFin_Timeout(_) => if (timeout) throw ErreurFormat else timeout = true
        }
        l = l2
      } }
    }

    if (!timeout) throw ErreurFormat
  }


  def verifAffichage(a: List[DAff]) = {
    var offset = false
    var zoom = false

    var l = a
    while (!l.isEmpty) {
      l match { case a :: l2 => {
        a match {
          case DAff_Offset(_) => if (offset) throw ErreurFormat else offset = true
          case DAff_Zoom(_) => if (zoom) throw ErreurFormat else zoom = true
        }
        l = l2
      } }
    }

    if (offset != zoom) throw ErreurFormat
  }
}
