
/** PROJET PROG 2
  * fenetre.scala
  */

package affichage

import jeu._

import scala.swing.event._
import scala.swing._
import scala.math._
import java.awt.image.BufferedImage
import java.awt.Image
import java.awt.Color
import java.io._
import javax.imageio.ImageIO
import javax.swing.Timer
import java.awt.event.ActionListener




class FenetreDeJeu(val carte: Carte) extends MainFrame {

  title = "TowerDefProj"

  /* DEFINITION TAILLE DE L'INTERFACE */

  // w : width, h : height

  val wFenetre = 800
  val hFenetre = 480
  // au dessus : seulement le canvas !
  val wMenuGauche = 100
  val hMenuBas = 100
  val wZoneVie = 200
  val hBouton = 30

  def hMenuGauche = hFenetre
  def wMenuBas = wFenetre-wMenuGauche
  def hZoneVie = hMenuBas
  def hZoneMessage = hMenuBas
  def wZoneMessage = wFenetre-wMenuGauche-wZoneVie
  def wBouton = wMenuGauche
  def wGrille = wFenetre-wMenuGauche
  def hGrille = hFenetre-hMenuBas

  /* MISE EN PLACE DE L'INTERFACE */

  val grille = new GrilleDeJeu(carte)

  val boutonTick = new Button { text = "Tick !" }
  val boutonPlacer = new Button { text = "Placer Tour" }
  val boutonVendre = new Button { text = "Vendre Tour" }
  val inventaire = new InventairePanel

  val menuGauche = new Panel {
    peer.setLayout(null)

    peer.add(boutonPlacer.peer)
    peer.add(boutonVendre.peer)
    peer.add(boutonTick.peer)
    peer.add(inventaire.peer)

    var yMax = 0
    def placerBouton(b:Button) = {
      b.peer.setBounds(0,yMax,wBouton,hBouton)
      yMax += hBouton
    }

    placerBouton(boutonPlacer)
    placerBouton(boutonVendre)
    placerBouton(boutonTick)

    inventaire.peer.setBounds(0,yMax,wMenuGauche,hFenetre-yMax)
  }

  val zoneMessage = new Label { text = "zone message" }
  val zoneVie = new ZoneVie(carte)

  val menuBas = new Panel {
    peer.setLayout(null)

    peer.add(zoneVie.peer)
    peer.add(zoneMessage.peer)

    zoneVie.peer.setBounds(0,0,wZoneVie,hZoneVie)
    zoneMessage.peer.setBounds(wZoneVie,0,wZoneMessage,hZoneMessage)
  }

  val contenuFenetre = new Panel {
    peer.setLayout(null)

    peer.add(menuGauche.peer)
    peer.add(grille.peer)
    peer.add(menuBas.peer)

    menuGauche.peer.setBounds(0,0,wMenuGauche,hMenuGauche)
    grille.peer.setBounds(wMenuGauche,0,wGrille,hGrille)
    menuBas.peer.setBounds(wMenuGauche,hGrille,wMenuBas,hMenuBas)
  }

  contents = contenuFenetre

  resizable = false
  size = new Dimension(wFenetre,hFenetre+25)



  /************** COLORATION POUR DEBUG **************/

  inventaire.background = Color.blue
  grille.background = Color.red
  zoneVie.background = Color.gray
  zoneMessage.background = Color.green


  /************* GESTION DES ÉVÈNEMENTS *************/

  // boutons
  listenTo(boutonTick)
  reactions += {
    case ButtonClicked(b) if b == boutonTick =>
      carte.tick
      repaint()
  }


  /*********************** TIMERS ********************/


  val fps = 60
  val timerAffichage = new Timer(1000/fps, new ActionListener {
    override def actionPerformed(e:java.awt.event.ActionEvent):Unit = {
      repaint()
print(".")
    }
  } )

  timerAffichage.start()


  var freqTick = 1
  val timerTick = new Timer(1000/freqTick, new ActionListener {
    override def actionPerformed(e:java.awt.event.ActionEvent):Unit = {
      carte.tick
println("TICK")
    }
  } )

  timerTick.start()


  /* OPÉRATIONS À EFFECTUER À LA FERMETURE DE LA FENÊTRE */

  override def closeOperation() = {
    timerAffichage.stop()
    timerTick.stop()

    System.exit(0)
  }

}
