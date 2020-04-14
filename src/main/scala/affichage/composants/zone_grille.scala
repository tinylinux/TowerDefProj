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

  var tailleCase: Int = CZoom.tailleCase
  var caseSel: Option[(Int, Int)] = None
  var maxX: Int = 0
  var maxY: Int = 0
  var offset: (Int, Int) = (0,0)
  var sourisSurSel: Boolean = true
  var posSouris: Point = new Point(0,0)

  /* EVENEMENTS */

  listenTo(mouse.wheel)

  reactions += { CZoneGrilleDND.react }

  override def paintComponent(
    g: Graphics2D
  ) =
    CZoneGrilleA.paintComp(g)

}
