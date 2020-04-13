/** TowerDefProj
  * dimensions.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._
import scala.Array


object DimJeu {

  /* DIMENSIONS DES COMPOSANTS */
  /* w : width, h : height */


  /* FenetreJeu */
  /* (seulement le canvas !) */
  val wFenetreJeu = 800
  val hFenetreJeu = 480


  /* MenuGauche, MenuBas, MenuGrille */
  val wMenuGauche = 100
  def hMenuGauche = hFenetreJeu
  val hMenuBas = 100
  def wMenuBas = wFenetreJeu-wMenuGauche  
  def wMenuGrille = wFenetreJeu-wMenuGauche
  def hMenuGrille = hFenetreJeu-hMenuBas


  /* ZoneBoutons, ZoneActions */
  def wZoneBoutons = wMenuGauche
  val hZoneBoutons = 40
  def wZoneActions = wMenuGauche
  def hZoneActions = 3*hBAction


  /* Inventaire */
  def wInventaire = wMenuGauche
  def hInventaire = hFenetreJeu-hZoneBoutons-hZoneActions


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


  /* Images ZoneVA */
  val lImgZVA = 40


  /* Tuiles ZoneGrille */
  val tabTuileZoneGrille = Array(8,12,16,24,32,48,64,96,128,192,256)
  val iTuileZoneGrilleInit = 6
}
