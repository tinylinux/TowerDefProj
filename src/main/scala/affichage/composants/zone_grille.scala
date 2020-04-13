/** TowerDefProj
  * zone_grille.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._


object ZoneGrille
    extends Selectionneur {

  /* SOUS COMPOSANT */

  this.peer.setLayout(null)
  MenuGrille.peer.add(this.peer)
  this.peer.setBounds(0,0,DimJeu.wZoneGrille,DimJeu.hZoneGrille)


  /* ATTRIBUTS */

  tailleCase = CZoom.tailleCase
  caseSel = None
  maxX = 0
  maxY = 0
  offset = (0,0)
  sourisSurSel = true
  posSouris = new Point(0,0)

  /* EVENEMENTS */

  listenTo(mouse.wheel)

  reactions += { CZoneGrilleDND.react }

  override def paintComponent(
    g: Graphics2D
  ) =
    CZoneGrilleA.paintComp(g)

}
