/** TowerDefProj
  * c_zone_va.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._

import java.awt.Graphics2D



object CZoneVA {

  val nomCoeur = "coeur.png"
  val nomDollar = "dollar.jpg"

  def paintComp(
    g: Graphics2D
  ) = {
    if (PartAff.partie.isDefined) {
      /* Dessiner les images */
      val i = DimJeu.hZoneVA-2*DimJeu.lImgZVA
      g.drawImage(CImgJeu.img(nomCoeur,
        (DimJeu.lImgZVA, DimJeu.lImgZVA)),
        i/3, i/3, null)
      g.drawImage(CImgJeu.img(nomDollar,
        (DimJeu.lImgZVA, DimJeu.lImgZVA)),
        i*2/3 + DimJeu.lImgZVA, i/3, null)

      /* Ecrire les pv restants, pv max et argent */
      val pv = PartAff.partie.get.carte.tP.pv
      val pvMax = PartAff.partie.get.carte.tP.pvMax
      val argent = PartAff.partie.get.argent
      val oldFont = g.getFont()
      g.setFont(new Font("Impact", Font.BOLD, 24))
      g.drawString(pv + "/" + pvMax, i*2/3 + DimJeu.lImgZVA, i/3 + DimJeu.lImgZVA)
      g.drawString(argent, i*2/3 + DimJeu.lImgZVA, i*2/3 + DimJeu.lImgZVA*2)
      g.setFont(oldFont)
    }
  }
