/** TowerDefProj
  * c_zoom.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._



object CZoom {

  var i = DimJeu.iTuileZoneGrilleInit

  def updateTC = {
    ZoneGrille.tailleCase = DimJeu.tabTuileZoneGrille(i)
  }


  def zoom(
    x: Int, // coordonnées sur l'écran auxquelles zoomer
    y: Int
  ) = {
    val aTC = ZoneGrille.tailleCase
    val aO = ZoneGrille.offset
    val nTC = DimJeu.tabTuileZoneGrille(i)
    ZoneGrille.tailleCase = nTC
    ZoneGrille.offset = (
      ( (aO._1*nTC) - (x*(nTC-aTC)) ) / aTC,
      ( (aO._2*nTC) - (y*(nTC-aTC)) ) / aTC
    ) // calcul magique
  }

  def plus = {
    if (i < DimJeu.tabTuileZoneGrille.length-1) {
      i += 1
      zoom(DimJeu.wZoneGrille/2, DimJeu.hZoneGrille/2)
    }
  }

  def moins = {
    if (i > 0) {
      i -= 1
      zoom(DimJeu.wZoneGrille/2, DimJeu.hZoneGrille/2)
    }
  }

  def centrer = {
    // trouver le zoom permettant d'afficher la carte en entier
    val tab = DimJeu.tabTuileZoneGrille
    val test = { z:Int => {
      z*ZoneGrille.maxX <= DimJeu.wZoneGrille && z*ZoneGrille.maxY <= DimJeu.hZoneGrille
    } }
    val tZip = tab.zipWithIndex.filter(
      { case (z:Int, _) => test(z) })
    i =
      if (tZip.isEmpty)
        tab.length-1 // aucune valeur de zoom ne permet d'afficher la carte en entier
      else tZip.maxBy( { case (z:Int, _) => z } )._2 // la liste des valeurs possibles n'est pas vide
    updateTC

    // ajuster l'offset
    ZoneGrille.offset =
      ((DimJeu.wZoneGrille - ZoneGrille.tailleCase*ZoneGrille.maxX)/2,
        (DimJeu.hZoneGrille - ZoneGrille.tailleCase*ZoneGrille.maxY)/2)
  }
    


}

