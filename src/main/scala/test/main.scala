/** TowerDefProj
  * main.scala
  */


/* PACKAGES */

package test
import jeu._
import affichage.comportements._
import affichage.composants._
import strategie._
import tours._
import ennemis._
import effets._

import scala.swing._
import java.awt.Color



object Main extends SimpleSwingApplication {

  def top = FenetreJeu

  override def main(args: Array[String]) {
    CPartAff.chargerPartie(PartieTest)

    Inventaire.peer.setBackground(Color.blue)
    ZoneVA.peer.setBackground(Color.red)
    ZoneInfos.peer.setBackground(Color.green)
    MenuGrille.peer.setBackground(Color.black)

    super.main(args)
  }

}
