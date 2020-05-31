/* TowerDefProj
 * charger.scala
 */


package sauvegardes

import jeu._
import affichage.composants._
import affichage.comportements._
import tours._
import ennemis._

import scala.util.parsing.combinator._
import scala.io._
import java.io._

object Charger {

  def charger(file: File) = {
    val in = Source.fromFile(file)
    val str = in.getLines.mkString
    in.close

    try {
      val res = ParserJeu.parse(ParserJeu.tdp, str)
      res match {
        case ParserJeu.Success(tdp:ParserJeu.DJeu,_) => {
          Verif.verif(tdp)

          val c = tdp._1 /* carte */
          val t = tdp._2 /* tours */
          val m = tdp._3 /* manches */
          val a = tdp._4 /* affichage */

          val (pa,ca,ma,gma) = instancesPartie()

          chargerCarte(ca, c)
          chargerGestionManches(gma, m)
          chargerTours(ca, t)
          chargerAffichage(a)

          CPartAff.chargerPartie(pa)
        }
        case _ => throw Verif.ErreurFormat
      }
    }
    catch {
      case Verif.ErreurFormat => print("ERREUR_CHARGEMENT")
    }
  }

  /* Renvoie des instances de Partie, Carte, Magasin et GestionManches
   * prêtes à être utilisées
   */
  def instancesPartie() = {
    val p = new Partie {
      var gM: GestionManches = null
      var mag: Magasin = null
      var carte: Carte = null
      var argent = 0
      var gagne: Option[Boolean] = None
    }
    val c = new Carte {
      var partie: Partie = null
      var tP: Tour = null
      var tours: List[Tour] = Nil
      var ennemis: List[Ennemi] = Nil
      var tuiles: Array[Array[Tuile]] = null
      var maxX: Int = 0
      var maxY: Int = 0
    }
    val m = new Magasin {
      var partie: Partie = null
      var contrats: List[Contrat] = Nil
    }
    val gm = new GestionManches {
      var partie: Partie = null
      var m: List[Manche] = Nil
      var mEnCours: Option[Manche] = None
    }

    p.gM = gm
    p.mag = m
    p.carte = c
    c.partie = p
    m.partie = p
    gm.partie = p

    (p,c,m,gm)
  }


  def chargerCarte(
    ca: Carte,
    c: List[DCarte]
  ) = {
    var l = c
    while (!l.isEmpty) {
      l match { case a :: l2 => {
        a match {
          case DCarte_MaxX(v) => ca.maxX = v
          case DCarte_MaxY(v) => ca.maxY = v
          case DCarte_Tuiles(t) => {
            var lt = t
            if (lt.isEmpty)
              ca.tuiles = new Array[Array[Tuile]](0)
            else {
              val x = lt(0).length
              val y = lt.length
              ca.tuiles = new Array[Array[Tuile]](x)
              for (i <- 0 until x)
                ca.tuiles(i) = new Array[Tuile](y)
              var i = 0 /* i <-> y (ligne) */
              while (!lt.isEmpty) {
                lt match { case s :: lt2 => {
                  var j = 0 /* j <-> x (colonne) */
                  var li = s.toList
                  while (!li.isEmpty) {
                    li match { case car :: li2 => {
                      car match {
                        case 'X' => ca.tuiles(j)(i) = Mur()
                        case 'O' => ca.tuiles(j)(i) = Sol()
                        case 'S' => ca.tuiles(j)(i) = Spawn()
                      }
                      li = li2
                      j = j+1
                    } }
                  }
                  lt = lt2
                  i = i+1
                } }
              }
            }
          }
        }
        l = l2
      } }
    }
  }


  def chargerGestionManches(
    gma: GestionManches,
    m: List[List[DManche]]
  ): Unit = {
    m match {
      case Nil => ()
      case a :: m2 =>
        chargerGestionManches(gma, m2)
        /* Chargement de la manche a */
        var man = new Manche {
          var gM: GestionManches = gma
          var fin: Int = 0
          var sTick: List[(TypeEndommageable, Posmt, Int)] = Nil
          var sPer: List[(TypeEndommageable, Posmt, Int)] = Nil
          var sAleat: List[(TypeEndommageable, Posmt, Double)] = Nil
          def actionFin: Unit = ()
        }
        var l = a
        while (!l.isEmpty) {
          l match { case b :: l2 => {
            b match {
              case DManche_Spawn(v) => chargerSpawn(man, v)
              case DManche_Fin(v) => chargerFin(man, v)
            }
            l = l2
          } }
        }
        man.sTick = man.sTick.sortBy( { case (_, _, i) => i } )
        gma.m = man :: gma.m
    }
  }


  def chargerFin(
    man: Manche,
    l: List[DFin]
  ): Unit = {
    l match {
      case Nil => ()
      case a :: l2 => {
        chargerFin(man, l2)
        a match {
          case DFin_Timeout(v) => man.fin = v
        }
      }
    }
  }


  def chargerSpawn(
    man: Manche,
    ldman: List[DSpawn]
  ) = {
    var enn = ""
    var posI = (0,0)
    var pos = (0.0, 0.0)
    var dPos = false
    var meth: DSpawn = null
    var l = ldman
    while (!l.isEmpty) {
      l match { case a :: l2 => {
        a match {
          case DSpawn_Ennemi(v) => enn = v
          case DSpawn_Pos(v) => { pos = v ; dPos = true }
          case DSpawn_X(v) => { posI = (v, posI._2) }
          case DSpawn_Y(v) => { posI = (posI._1, v) }
          case o => meth = o
        }
        l = l2
      } }
    }
    var posmt = {
      if (!dPos) Posmt_Int(posI)
      else Posmt_Double(pos)
    }
    var typeEnn = { enn match {
      case "Fourmi" => TypeFourmi
      case "Kamikaze" => TypeKamikaze
      case "Johnson" => TypeJohnson
      case "Racaillou" => TypeRacaillou
      case "Soignant" => TypeSoignant
    } }
    meth match {
      case DSpawn_Tick(v) =>
        man.sTick = (typeEnn, posmt, v) :: man.sTick
      case DSpawn_Per(v) =>
        man.sPer = (typeEnn, posmt, v) :: man.sPer
      case DSpawn_Aleat(v) =>
        man.sAleat = (typeEnn, posmt, v) :: man.sAleat
    }
  }



  def chargerTours(
    ca: Carte,
    t: List[List[DTour]]
  ) = {
    var l = t
    var tp = true
    while (!l.isEmpty) {
      l match { case a :: l2 => {
        chargerTour(ca, a)
        if (tp) {
          ca.tP = ca.tours(0)
          tp = false
        }
        l = l2
      } }
    }
  }


  def chargerTour(
    ca: Carte,
    ldtour: List[DTour]
  ) = {
    var tour = ""
    var posI = (0,0)
    var pvMax: Option[Int] = None
    var pv: Option[Int] = None
    var portee: Option[Double] = None
    var rayon: Option[Double] = None
    var deg: Option[Int] = None
    var soin: Option[Int] = None
    var cooldown: Option[Int] = None
    var cooldownAct: Option[Int] = None
    var l = ldtour
    while (!l.isEmpty) {
      l match { case a :: l2 => {
        a match {
          case DTour_Tour(v) => tour = v
          case DTour_X(v) => { posI = (v, posI._2) }
          case DTour_Y(v) => { posI = (posI._1, v) }
          case DTour_PvMax(v) => pvMax = Some(v)
          case DTour_Pv(v) => pv = Some(v)
          case DTour_Portee(v) => portee = Some(v)
          case DTour_Rayon(v) => rayon = Some(v)
          case DTour_Deg(v) => deg = Some(v)
          case DTour_Soin(v) => soin = Some(v)
          case DTour_Cooldown(v) => cooldown = Some(v)
          case DTour_CooldownAct(v) => cooldownAct = Some(v)
        }
        l = l2
      } }
    }
    var instTour = { tour match {
      case "Defenseuse" => new Defenseuse
      case "TourPrincipale" => new TourPrincipale
      case "Barriere" => new Barriere
      case "Sniper" => new Sniper
      case "Gluant" => new Gluant
      case "Yogi" => new Yogi
      case "Mortier" => new Mortier
    } }
    pv match {
      case Some(v) => instTour.pv = v
      case None => ()
    }
    pvMax match {
      case Some(v) => instTour.pvMax = v
      case None => ()
    }
    portee match {
      case Some(v) => instTour.portee = v
      case None => ()
    }
    rayon match {
      case Some(v) => instTour.rayon = v
      case None => ()
    }
    deg match {
      case Some(v) => instTour.deg = v
      case None => ()
    }
    soin match {
      case Some(v) => instTour.soin = v
      case None => ()
    }
    cooldown match {
      case Some(v) => instTour.cooldown = v
      case None => ()
    }
    cooldownAct match {
      case Some(v) => instTour.cooldownAct = v
      case None => ()
    }
    ca.spawnT(instTour, posI)
  }


  def chargerAffichage(
    l: List[DAff]
  ): Unit = {
    l match {
      case Nil => ()
      case a :: l2 => {
        chargerAffichage(l2)
        a match {
          case DAff_Offset(v) => ZoneGrille.offset = v
          case DAff_Zoom(v) => CZoom.i = v
        }
      }
    }
  }

}
