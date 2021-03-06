/** TowerDefProj
  * c_zone_infos.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._

import java.awt.{Graphics2D, Font}



object CZoneInfos {

  def infosContrat(
    c: Contrat
  ) = {
    ZoneInfos.t = None
    ZoneInfos.c = Some(c)
  }

  def infosTour(
    t: Tour
  ) = {
    ZoneInfos.t = Some(t)
    ZoneInfos.c = None
  }

  def paintComp(
    g: Graphics2D
  ) = {
    val oldFont = g.getFont()
    g.setFont(new Font("Impact", Font.PLAIN, 16))
    val i = DimJeu.hZoneInfos - DimJeu.lImgZI
    if (ZoneInfos.c.isDefined) {
      // affichage d'un contrat
      val c = ZoneInfos.c.get
      c.typeAnc match {
        case Some(_) => afficherUpgrade(g, c)
        // le contrat est une amélioration de tour
        case None => afficherSpawn(g, c)
          // le contrat est un achat de tour
      }
    }
    else if (ZoneInfos.t.isDefined) {
      // affichage d'une tour
      val t = ZoneInfos.t.get
      afficherTour(g, t)
    }
    g.setFont(oldFont)
  }


  def afficherTour(
    g: Graphics2D,
    t: Tour
  ) = {
    val i = DimJeu.hZoneInfos - DimJeu.lImgZI
    g.drawImage(CImgJeu.img(t.typeE.img,
      (DimJeu.lImgZI, DimJeu.lImgZI)),
      i/2, i/2, null)
    g.drawString(t.typeE.nom, DimJeu.xTxt1, 30)
    g.drawString("PV:" + t.pv.toString + "/" + t.pvMax.toString, DimJeu.xTxt1, 60)
    g.drawString("COOLDOWN:" + t.cooldown.toString + "/" + t.cooldownAct.toString, DimJeu.xTxt1, 90)
    g.drawString("DEGATS:" + t.deg.toString, DimJeu.xTxt2, 30)
    g.drawString("SOIN:" + t.soin.toString, DimJeu.xTxt2, 60)
    g.drawString("PORTEE:" + t.portee.toString, DimJeu.xTxt3, 30)
    g.drawString("RAYON:" + t.rayon.toString, DimJeu.xTxt3, 60)
  }


  def afficherUpgrade(
    g: Graphics2D,
    c: Contrat
  ) = {
    val i = DimJeu.hZoneInfos - DimJeu.lImgZI
    g.drawString("UPGRADE", i/2, 40)
    g.drawString("COUT : " + c.prix.toString, i/2, 90)
    g.drawImage(CImgJeu.img(c.typeAnc.get.img,
      (DimJeu.lImgZI, DimJeu.lImgZI)),
      DimJeu.xImg1, i/2, null)
    g.drawString("ORIGINE", DimJeu.xImg1 + i/2 + DimJeu.lImgZI, 40)
    g.drawString(c.typeAnc.get.nom, DimJeu.xImg1 + i/2 + DimJeu.lImgZI, 90)
    g.drawImage(CImgJeu.img(c.typeNouv.img,
      (DimJeu.lImgZI, DimJeu.lImgZI)),
      DimJeu.xImg2, i/2, null)
    g.drawString("EVOLUTION", DimJeu.xImg2 + i/2 + DimJeu.lImgZI, 40)
    g.drawString(c.typeNouv.nom, DimJeu.xImg2 + i/2 + DimJeu.lImgZI, 90)
  }


  def afficherSpawn(
    g: Graphics2D,
    c: Contrat
  ) = {
    val i = DimJeu.hZoneInfos - DimJeu.lImgZI
    g.drawString("SPAWN", i/2, 40)
    g.drawString("COUT : " + c.prix.toString, i/2, 90)
    g.drawImage(CImgJeu.img(c.typeNouv.img,
      (DimJeu.lImgZI, DimJeu.lImgZI)),
      DimJeu.xImg1, i/2, null)
    g.drawString("NOUVELLE TOUR", DimJeu.xImg1 + i/2 + DimJeu.lImgZI, 40)
    g.drawString(c.typeNouv.nom, DimJeu.xImg1 + i/2 + DimJeu.lImgZI, 90)
  }

}
