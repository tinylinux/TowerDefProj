/** TowerDefProj
  * c_zone_va.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._

import java.awt.{Graphics2D, Font}



object CZoneVA {

  val nomCoeur = "coeur.png"
  val nomDollar = "dollar.jpg"

  def paintComp(
    g: Graphics2D
  ) = {
    if (CPartAff.partie.isDefined) {
      /* Dessiner les images */
      val i = DimJeu.hZoneVA-2*DimJeu.lImgZVA
      g.drawImage(CImgJeu.img(nomCoeur,
        (DimJeu.lImgZVA, DimJeu.lImgZVA)),
        i/3, i/3, null)
      g.drawImage(CImgJeu.img(nomDollar,
        (DimJeu.lImgZVA, DimJeu.lImgZVA)),
        i*2/3 + DimJeu.lImgZVA, i/3, null)

      /* Ecrire les pv restants, pv max et argent */
      val pv = CPartAff.partie.get.carte.tP.pv.toString
      val pvMax = CPartAff.partie.get.carte.tP.pvMax.toString
      val argent = CPartAff.partie.get.argent.toString
      val oldFont = g.getFont()
      g.setFont(new Font("Impact", Font.BOLD, 24))
      g.drawString(pv + "/" + pvMax, i*2/3 + DimJeu.lImgZVA, i/3 + DimJeu.lImgZVA)
      g.drawString(argent, i*2/3 + DimJeu.lImgZVA, i*2/3 + DimJeu.lImgZVA*2)
      g.setFont(oldFont)
    }
  }

}
