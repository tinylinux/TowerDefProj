/* TowerDefProj
 * enregistrer.scala
 */


package sauvegardes

import jeu._
import affichage.composants._
import affichage.comportements._
import tours._
import ennemis._

import java.io.{File, FileWriter}
import java.lang.StringBuilder

object Enregistrer {

  def enregistrer(file: File) = {
    try {
      if (file.exists())
        file.delete()
      file.createNewFile()
      val out = new FileWriter(file)

      val p = CPartAff.partie.get
      out.write(enregCarte(p.carte))
      out.write(enregTour(
        p.carte.tours.find( {
          t => (t == p.carte.tP) } ).get,
        true ) )
      p.carte.tours.filter( {
        t => (t != p.carte.tP)
      } ).foreach( {
        t => out.write(enregTour(t, false))
      } )
      p.gM.m.foreach( {
        m => out.write(enregManche(m))
      } )
      out.write(enregAffichage)

      out.close()
    }
    catch {
      case Verif.ErreurFormat => print("ERREUR_ENREGISTREMENT")
    }
  }


  def enregCarte(
    c: Carte
  ) = {
    val sb = new StringBuilder()
    sb.append("CARTE {\n")
    sb.append("  maxX: ")
    sb.append(c.maxX.toString())
    sb.append("\n")
    sb.append("  maxY: ")
    sb.append(c.maxY.toString())
    sb.append("\n")
    sb.append("  TUILES {\n")
    for (j <- 0 until c.maxY) {
      sb.append("    ")
      for (i <- 0 until c.maxX) {
        c.tuiles(i)(j) match {
          case Sol() => sb.append('O')
          case Mur() => sb.append('X')
          case Spawn() => sb.append('S')
        }
      }
      sb.append("\n")
    }
    sb.append("  }\n")
    sb.append("}\n\n")
    sb.toString()
  }


  def enregTour(
    t: Tour,
    tp: Boolean
  ) = {
    val sb = new StringBuilder()
    sb.append("TOUR ")
    if (tp) sb.append("PRINCIPALE ")
    sb.append("{\n")
    sb.append("  tour: ")
    sb.append( t.typeE match {
      case TypeTourPrincipale => "TourPrincipale"
      case TypeDefenseuse => "Defenseuse"
      case TypeSniper => "Sniper"
      case TypeMortier => "Mortier"
      case TypeLampadaire => "Lampadaire"
      case TypeYogi => "Yogi"
      case TypeBarriere => "Barriere"
    } )
    sb.append("\n")
    sb.append("  x: ")
    sb.append(t.pos.get._1.toInt.toString)
    sb.append("\n")
    sb.append("  y: ")
    sb.append(t.pos.get._2.toInt.toString)
    sb.append("\n")
    sb.append("}\n\n")
    sb.toString()
  }


  def enregManche(
    m: Manche
  ) = {
    val sb = new StringBuilder()

    def appEnnPos(
      e: TypeEndommageable,
      p: Posmt
    ) = {
      sb.append("    ennemi: ")
      sb.append( e match {
        case TypeFourmi => "Fourmi"
        case TypeRacaillou => "Racaillou"
        case TypeJohnson => "Johnson"
        case TypeSoignant => "Soignant"
        case TypeKamikaze => "Kamikaze"
      } )
      sb.append("\n")
      p match {
        case Posmt_Double(v) => {
          sb.append("    pos: ")
          sb.append(v.toString)
          sb.append("\n")
        }
        case Posmt_Int(v) => {
          sb.append("    x: ")
          sb.append(v._1.toString)
          sb.append("\n")
          sb.append("    y: ")
          sb.append(v._2.toString)
          sb.append("\n")
        }
      }
    }

    def appSpawn(
      s: (TypeEndommageable, Posmt, Any),
      str: String
    ) = {
      sb.append("  SPAWN {\n")
      appEnnPos(s._1, s._2)
      sb.append("    ")
      sb.append(str)
      sb.append(": ")
      sb.append(s._3.toString)
      sb.append("\n")
      sb.append("  }\n\n")
    }

    sb.append("MANCHE {\n")
    m.sTick.foreach( {
      s => appSpawn(s, "tick")
    } )
    m.sPer.foreach( {
      s => appSpawn(s, "per")
    } )
    m.sAleat.foreach( {
      s => appSpawn(s, "aleat")
    } )
    sb.append("  FIN {\n")
    sb.append("    timeout: ")
    sb.append(m.fin.toString())
    sb.append("\n")
    sb.append("  }\n")
    sb.append("}\n\n")
    sb.toString()
  }


  def enregAffichage = {
    val sb = new StringBuilder()
    sb.append("AFFICHAGE {\n")
    sb.append("  offset: ")
    sb.append(ZoneGrille.offset.toString)
    sb.append("\n")
    sb.append("  zoom: ")
    sb.append(CZoom.i.toString)
    sb.append("\n")
    sb.append("}\n")
    sb.toString
  }
}
