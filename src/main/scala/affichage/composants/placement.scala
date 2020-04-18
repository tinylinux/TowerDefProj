/** TowerDefProj
  * placement.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._
import javax.swing.JLayeredPane


object Placement {

  /* Ajoute les sous-composants à leur composant parent
   * et met en place leur disposition
   * On commence par les feuilles pour remonter à la racine
   */
  def placerComposants = {

    /* JLayeredPane MenuGrille */

    val jlp = new JLayeredPane()

    /* SET LAYOUT NULL */

    ZoneActions.peer.setLayout(null)
    ZoneBoutons.peer.setLayout(null)
    MenuGrille.peer.setLayout(null)
    MenuGauche.peer.setLayout(null)
    MenuBas.peer.setLayout(null)
    jlp.setLayout(null)


    /* ADD */

    /* ZoneActions */

    ZoneActions.peer.add(BAttaque.peer)
    ZoneActions.peer.add(BAcheter.peer)
    ZoneActions.peer.add(BDetruire.peer)


    /* ZoneBoutons */

    ZoneBoutons.peer.add(BPause.peer)
    ZoneBoutons.peer.add(BSlow.peer)
    ZoneBoutons.peer.add(BFast.peer)
    ZoneBoutons.peer.add(BParam.peer)


    /* MenuGauche */

    MenuGauche.peer.add(Inventaire.peer)
    MenuGauche.peer.add(ZoneBoutons.peer)
    MenuGauche.peer.add(ZoneActions.peer)


    /* MenuGrille et jlp */

    MenuGrille.peer.add(jlp)
    jlp.add(ZoneGrille.peer, JLayeredPane.DEFAULT_LAYER)
    jlp.add(BMoins.peer, JLayeredPane.PALETTE_LAYER)
    jlp.add(BPlus.peer, JLayeredPane.PALETTE_LAYER)
    jlp.add(BCentre.peer, JLayeredPane.PALETTE_LAYER)


    /* MenuBas */

    MenuBas.peer.add(ZoneInfos.peer)
    MenuBas.peer.add(ZoneVA.peer)




    /* SET BOUNDS */

    /* ZoneActions */

    BAttaque.peer.setBounds(0,0,DimJeu.wBAction,DimJeu.hBAction)
    BAcheter.peer.setBounds(0,DimJeu.hBAction,DimJeu.wBAction,DimJeu.hBAction)
    BDetruire.peer.setBounds(0,DimJeu.hBAction*2,DimJeu.wBAction,DimJeu.hBAction)


    /* ZoneBoutons */

    BPause.peer.setBounds(
      (DimJeu.wZoneBoutons-4*DimJeu.lBJeu)/5,
      (DimJeu.hZoneBoutons-DimJeu.lBJeu)/2,
      DimJeu.lBJeu,DimJeu.lBJeu)
    BSlow.peer.setBounds(
      (DimJeu.wZoneBoutons-4*DimJeu.lBJeu)*2/5 + DimJeu.lBJeu,
      (DimJeu.hZoneBoutons-DimJeu.lBJeu)/2,
      DimJeu.lBJeu,DimJeu.lBJeu)
    BFast.peer.setBounds(
      (DimJeu.wZoneBoutons-4*DimJeu.lBJeu)*3/5 + DimJeu.lBJeu*2,
      (DimJeu.hZoneBoutons-DimJeu.lBJeu)/2,
      DimJeu.lBJeu,DimJeu.lBJeu)
    BParam.peer.setBounds(
      (DimJeu.wZoneBoutons-4*DimJeu.lBJeu)*4/5 + DimJeu.lBJeu*3,
      (DimJeu.hZoneBoutons-DimJeu.lBJeu)/2,
      DimJeu.lBJeu,DimJeu.lBJeu)


    /* MenuGauche */

    Inventaire.peer.setBounds(0,DimJeu.hZoneBoutons+DimJeu.hZoneActions,DimJeu.wInventaire,DimJeu.hInventaire)
    ZoneBoutons.peer.setBounds(0,0,DimJeu.wZoneBoutons,DimJeu.hZoneBoutons)
    ZoneActions.peer.setBounds(0,DimJeu.hZoneBoutons,DimJeu.wZoneActions,DimJeu.hZoneActions)


    /* MenuGrille et jlp */

    jlp.setBounds(0,0,DimJeu.wMenuGrille,DimJeu.hMenuGrille)
    ZoneGrille.peer.setBounds(0,0,DimJeu.wZoneGrille,DimJeu.hZoneGrille)
    BMoins.peer.setBounds(
      DimJeu.wMenuGrille - 3*DimJeu.lBZoom - 3*DimJeu.dlBZoom,
      DimJeu.dlBZoom,
      DimJeu.lBZoom, DimJeu.lBZoom)
    BPlus.peer.setBounds(
      DimJeu.wMenuGrille - 2*DimJeu.lBZoom - 2*DimJeu.dlBZoom,
      DimJeu.dlBZoom,
      DimJeu.lBZoom, DimJeu.lBZoom)
    BCentre.peer.setBounds(
      DimJeu.wMenuGrille - DimJeu.lBZoom - DimJeu.dlBZoom,
      DimJeu.dlBZoom,
      DimJeu.lBZoom, DimJeu.lBZoom)


    /* MenuBas */

    ZoneInfos.peer.setBounds(DimJeu.wZoneVA,0,DimJeu.wZoneInfos,DimJeu.hZoneInfos)
    ZoneVA.peer.setBounds(0,0,DimJeu.wZoneVA,DimJeu.hZoneVA)



    /* PLACEMENT FENETRE */

    FenetreJeu.contents = new Panel {
      peer.setLayout(null)

      peer.add(MenuBas.peer)
      peer.add(MenuGauche.peer)
      peer.add(MenuGrille.peer)
    }

    MenuBas.peer.setBounds(DimJeu.wMenuGauche,DimJeu.hMenuGrille,DimJeu.wMenuBas,DimJeu.hMenuBas)
    MenuGauche.peer.setBounds(0,0,DimJeu.wMenuGauche,DimJeu.hMenuGauche)
    MenuGrille.peer.setBounds(DimJeu.wMenuGauche,0,DimJeu.wMenuGrille,DimJeu.hMenuGrille)



  }

}
