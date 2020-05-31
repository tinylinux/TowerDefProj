/* TowerDefProj
 * parser.scala
 * Ce fichier contient les définitions des différents terminaux, combinateurs et règles
 * utilisés pour parser les fichiers .tdp.
 */


package sauvegardes

import scala.util.parsing.combinator._


/* CASE CLASSES
 * correspondant aux non terminaux, auxquels on attribue une valeur
 * D: Déclaration
 */


trait DCarte
case class DCarte_MaxX(v: Int) extends DCarte
case class DCarte_MaxY(v: Int) extends DCarte
case class DCarte_Argent(v: Int) extends DCarte
case class DCarte_Tuiles(d: List[String]) extends DCarte

trait DTour
case class DTour_Tour(v: String) extends DTour
case class DTour_X(v: Int) extends DTour
case class DTour_Y(v: Int) extends DTour
case class DTour_PvMax(v: Int) extends DTour
case class DTour_Pv(v: Int) extends DTour
case class DTour_CooldownAct(v: Int) extends DTour
case class DTour_Cooldown(v: Int) extends DTour
case class DTour_Deg(v: Int) extends DTour
case class DTour_Soin(v: Int) extends DTour
case class DTour_Portee(v: Double) extends DTour
case class DTour_Rayon(v: Double) extends DTour

trait DSpawn
case class DSpawn_Ennemi(v: String) extends DSpawn
case class DSpawn_Pos(v: (Double,Double)) extends DSpawn
case class DSpawn_X(v: Int) extends DSpawn
case class DSpawn_Y(v: Int) extends DSpawn
case class DSpawn_Tick(v: Int) extends DSpawn
case class DSpawn_Per(v: Int) extends DSpawn
case class DSpawn_Aleat(v: Double) extends DSpawn

trait DFin
case class DFin_Timeout(v: Int) extends DFin

trait DManche
case class DManche_Spawn(v: List[DSpawn]) extends DManche
case class DManche_Fin(v: List[DFin]) extends DManche

trait DAff
case class DAff_Offset(v: (Int,Int)) extends DAff
case class DAff_Zoom(v: Int) extends DAff



object ParserJeu extends RegexParsers {

  type DJeu = (List[DCarte],List[List[DTour]],List[List[DManche]],List[DAff])

  val str: Parser[String] = "[a-zA-Z_]+".r
  val int: Parser[Int] = "[0-9]+".r ^^ { _.toInt }
  val double: Parser[Double] = """[0-9]+"."[0-9]+""".r ^^ { _.toDouble }

  def tdp: Parser[DJeu] = {
    phrase(bCarte ~ bTp ~ tours ~ manches ~ affichage) ^^ {
      case c ~ tP ~ t ~ m ~ a => (c, tP :: t, m, a)
    }
  }

  def bCarte: Parser[List[DCarte]] = {
    "CARTE" ~ "{" ~ dCarte ~ "}" ^^ {
      case _ ~ _ ~ c ~ _ => c
    }
  }

  def dCarte: Parser[List[DCarte]] = {
    def rMaxX = "maxX" ~ ":" ~ int ~ dCarte ^^ {
      case _ ~ _ ~ v ~ d => DCarte_MaxX(v) :: d
    }
    def rMaxY = "maxY" ~ ":" ~ int ~ dCarte ^^ {
      case _ ~ _ ~ v ~ d => DCarte_MaxY(v) :: d
    }
    def rArgent = "argent" ~ ":" ~ int ~ dCarte ^^ {
      case _ ~ _ ~ v ~ d => DCarte_Argent(v) :: d
    }
    def rTuiles = bTuile ~ dCarte ^^ {
      case b ~ d => DCarte_Tuiles(b) :: d
    }
    def rEps = "" ^^ { _ => Nil }

    rMaxX | rMaxY | rArgent | rTuiles | rEps
  }

  def bTuile: Parser[List[String]] = {
    "TUILES" ~ "{" ~ dTuile ~ "}" ^^ {
      case _ ~ _ ~ d ~ _ => d
    }
  }

  def dTuile: Parser[List[String]] = {
    def rStr = str ~ dTuile ^^ {
      case v ~ d => v :: d
    }
    def rEps = "" ^^ { _ => Nil }

    rStr | rEps
  }

  def bTp: Parser[List[DTour]] = {
    "TOUR" ~ "PRINCIPALE" ~ "{" ~ dTour ~ "}" ^^ {
      case _ ~ _ ~ _ ~ d ~ _ => d
    }
  }

  def tours: Parser[List[List[DTour]]] = {
    def rTours = bTour ~ tours ^^ {
      case b ~ d => b :: d
    }
    def rEps = "" ^^ { _ => Nil }

    rTours | rEps
  }

  def bTour: Parser[List[DTour]] = {
    "TOUR" ~ "{" ~ dTour ~ "}" ^^ {
      case _ ~ _ ~ d ~ _ => d
    }
  }

  def dTour: Parser[List[DTour]] = {
    def rTour = "tour" ~ ":" ~ str ~ dTour ^^ {
      case _ ~ _ ~ v ~ d => DTour_Tour(v) :: d
    }
    def rX = "x" ~ ":" ~ int ~ dTour ^^ {
      case _ ~ _ ~ v ~ d => DTour_X(v) :: d
    }
    def rY = "y" ~ ":" ~ int ~ dTour ^^ {
      case _ ~ _ ~ v ~ d => DTour_Y(v) :: d
    }
    def rPvMax = "pvMax" ~ ":" ~ int ~ dTour ^^ {
      case _ ~ _ ~ v ~ d => DTour_PvMax(v) :: d
    }
    def rPv = "pv" ~ ":" ~ int ~ dTour ^^ {
      case _ ~ _ ~ v ~ d => DTour_Pv(v) :: d
    }
    def rCooldownAct = "cooldownAct" ~ ":" ~ int ~ dTour ^^ {
      case _ ~ _ ~ v ~ d => DTour_CooldownAct(v) :: d
    }
    def rCooldown = "cooldown" ~ ":" ~ int ~ dTour ^^ {
      case _ ~ _ ~ v ~ d => DTour_Cooldown(v) :: d
    }
    def rDeg = "deg" ~ ":" ~ int ~ dTour ^^ {
      case _ ~ _ ~ v ~ d => DTour_Deg(v) :: d
    }
    def rSoin = "soin" ~ ":" ~ int ~ dTour ^^ {
      case _ ~ _ ~ v ~ d => DTour_Soin(v) :: d
    }
    def rPortee = "portee" ~ ":" ~ double ~ dTour ^^ {
      case _ ~ _ ~ v ~ d => DTour_Portee(v) :: d
    }
    def rRayon = "rayon" ~ ":" ~ double ~ dTour ^^ {
      case _ ~ _ ~ v ~ d => DTour_Rayon(v) :: d
    }
    def rEps = "" ^^ { _ => Nil }

    rTour | rX | rY | rPvMax | rPv | rCooldownAct | rCooldown | rDeg |
    rSoin | rPortee | rRayon | rEps
  }

  def manches: Parser[List[List[DManche]]] = {
    def rManches = bManche ~ manches ^^ {
      case b ~ m => b :: m
    }
    def rEps = "" ^^ { _ => Nil }

    rManches | rEps
  }

  def bManche: Parser[List[DManche]] = {
    "MANCHE" ~ "{" ~ dManche ~ "}" ^^ {
      case _ ~ _ ~ d ~ _ => d
    }
  }

  def dManche: Parser[List[DManche]] = {
    def rSpawn = bSpawn ~ dManche ^^ {
      case b ~ d => DManche_Spawn(b) :: d
    }
    def rFin = bFin ~ dManche ^^ {
      case b ~ d => DManche_Fin(b) :: d
    }
    def rEps = "" ^^ { _ => Nil }

    rSpawn | rFin | rEps
  }

  def bSpawn: Parser[List[DSpawn]] = {
    "SPAWN" ~ "{" ~ dSpawn ~ "}" ^^ {
      case _ ~ _ ~ d ~ _ => d
    }
  }

  def dSpawn: Parser[List[DSpawn]] = {
    def rEnnemi = "ennemi" ~ ":" ~ str ~ dSpawn ^^ {
      case _ ~ _ ~ v ~ d => DSpawn_Ennemi(v) :: d
    }
    def rPos = "pos" ~ ":" ~ "(" ~ double ~ "," ~ double ~ ")" ~ dSpawn ^^ {
      case _ ~ _ ~ _ ~ v1 ~ _ ~ v2 ~ _ ~ d => DSpawn_Pos((v1,v2)) :: d
    }
    def rX = "x" ~ ":" ~ int ~ dSpawn ^^ {
      case _ ~ _ ~ v ~ d => DSpawn_X(v) :: d
    }
    def rY = "y" ~ ":" ~ int ~ dSpawn ^^ {
      case _ ~ _ ~ v ~ d => DSpawn_Y(v) :: d
    }
    def rTick = "tick" ~ ":" ~ int ~ dSpawn ^^ {
      case _ ~ _ ~ v ~ d => DSpawn_Tick(v) :: d
    }
    def rPer = "per" ~ ":" ~ int ~ dSpawn ^^ {
      case _ ~ _ ~ v ~ d => DSpawn_Per(v) :: d
    }
    def rAleat = "aleat" ~ ":" ~ int ~ dSpawn ^^ {
      case _ ~ _ ~ v ~ d => DSpawn_Aleat(v) :: d
    }
    def rEps = "" ^^ { _ => Nil }

    rEnnemi | rPos | rX | rY | rTick | rPer | rAleat | rEps
  }

  def bFin: Parser[List[DFin]] = {
    "FIN" ~ "{" ~ dFin ~ "}" ^^ {
      case _ ~ _ ~ d ~ _ => d
    }
  }

  def dFin: Parser[List[DFin]] = {
    def rTimeout = "timeout" ~ ":" ~ int ~ dFin ^^ {
      case _ ~ _ ~ v ~ d => DFin_Timeout(v) :: d
    }
    def rEps = "" ^^ { _ => Nil }

    rTimeout | rEps
  }

  def affichage: Parser[List[DAff]] = {
    def rEps = "" ^^ { _ => Nil }

    bAff | rEps
  }

  def bAff: Parser[List[DAff]] = {
    "AFFICHAGE" ~ "{" ~ dAff ~ "}" ^^ {
      case _ ~ _ ~ d ~ _ => d
    }
  }

  def dAff: Parser[List[DAff]] = {
    def rOffset = "offset" ~ ":" ~ "(" ~ int ~ "," ~ int ~ ")" ~ dAff ^^ {
      case _ ~ _ ~ _ ~ v1 ~ _ ~ v2 ~ _ ~ d => DAff_Offset((v1, v2)) :: d
    }
    def rZoom = "zoom" ~ ":" ~ int ~ dAff ^^ {
      case _ ~ _ ~ v ~ d => DAff_Zoom(v) :: d
    }
    def rEps = "" ^^ { _ => Nil }

    rOffset | rZoom | rEps
  }


}
