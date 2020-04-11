/** TowerDefProj
  * dimensions.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu

import scala.swing._


object DimJeu {

  /* DIMENSIONS DES COMPOSANTS */
  /* w : width, h : height */


  /* FenetreJeu */
  /* (seulement le canvas !) */
  val wFenetreJeu = 800
  val hFenetreJeu = 480


  /* MenuGauche, MenuBas, MenuGrille */
  val wMenuGauche = 100
  def hMenuGauche = hFenetre
  val hMenuBas = 100
  def wMenuBas = wFenetre-wMenuGauche  
  def wMenuGrille = wFenetre-wMenuGauche
  def hMenuGrille = hFenetre-hMenuBas


  /* ZoneBoutons, ZoneActions */
  def wZoneBoutons = wMenuGauche
  val hZoneBoutons = 40
  def wZoneActions = wMenuGauche
  def hZoneActions = 3*hBAction


  /* Inventaire */
  def wInventaire = wMenuGauche
  def hInventaire = hFenetre-hZoneBoutons-hZoneActions


  /* ZoneVA, ZoneInfos */
  val wZoneVA = 200
  def hZoneVA = hMenuBas
  def wZoneInfos = wMenuBas-wZoneVA
  def hZoneInfos = hMenuBas


  /* ZoneGrille */
  def wZoneGrille = wMenuGrille
  def hZoneGrille = hMenuGrille


  /* BJeu (boutons de ZoneBoutons) */
  val lBJeu = 20


  /* BZoom (boutons de ZoneGrille) */
  val lBZoom = 15
  val dlBZoom = 10 /* Distance du bord */


  /* BAction */
  def wBAction = wMenuGauche
  val hBAction = 30


}
