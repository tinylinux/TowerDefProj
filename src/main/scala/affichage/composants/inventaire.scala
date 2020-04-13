/** TowerDefProj
  * inventaire.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._
import java.awt.Point


object Inventaire
    extends Selectionneur {

  /* SOUS COMPOSANT */

  MenuGauche.peer.add(this.peer)
  this.peer.setBounds(0,DimJeu.hZoneBoutons+DimJeu.hZoneActions,DimJeu.wInventaire,DimJeu.hInventaire)


  /* ATTRIBUTS */

  tailleCase = DimJeu.wInventaire/2
  caseSel = None
  maxX = 0
  maxY = 0
  offset = (0,0)
  sourisSurSel = true
  posSouris = new Point(0,0)

  var offsetMax = 0
  var dOffset = 10 // nb de pixels d'offset par evt de molette

  /* EVENEMENTS */

  listenTo(mouse.wheel)

  reactions += { CInventaire.react }

  override def paintComponent(
    g: Graphics2D
  ) =
    CInventaire.paintComp(g)
}
