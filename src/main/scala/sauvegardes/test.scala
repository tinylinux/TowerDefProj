/* TowerDefProj
 * parser.scala
 * Ce fichier contient les définitions des différents terminaux, combinateurs et règles
 * utilisés pour parser les fichiers .tdp.
 */


package sauvegardes

import scala.util.parsing.combinator._
import scala.io._


object TestJeu {

  def main(args: Array[String]) = {

    val f = Source.fromFile("src/main/resources/saves/partie.tdp")
    val str = f.getLines.mkString
    f.close

    val res = ParserJeu.parse(ParserJeu.tdp, str)
    res match { case ParserJeu.Success(tdp,_) => Verif.verif(tdp) }
    println(res)
  }

}
