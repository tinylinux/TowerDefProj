/** TowerDefProj
  * main.scala
  */


/* PACKAGES */

package test
import jeu._
import affichage.comportements._
import strategie._
import tours._
import ennemis._
import effets._


object Main {

  def main(args: Array[String]) {
    CPartAff.chargerPartie(PartieTest)
  }

}
