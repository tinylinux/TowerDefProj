
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

    val hBouton = 30
    var yMax = 0
    def placerBouton(b:Button) = {
      b.peer.setBounds(0,yMax,100,hBouton)
      yMax += hBouton
    }

    placerBouton(boutonPlacer)
    placerBouton(boutonVendre)
    placerBouton(boutonTick)

    inventaire.peer.setBounds(0,yMax,100,480)
  }

  val zoneMessage = new Label { text = "zone message" }
  val zoneVie = new Label { text = "zone vie" }

  val menuBas = new Panel {
    peer.setLayout(null)

    peer.add(zoneVie.peer)
    peer.add(zoneMessage.peer)

    zoneVie.peer.setBounds(0,0,100,100)
    zoneMessage.peer.setBounds(100,0,600,100)
  }

  val contenuFenetre = new Panel {
    peer.setLayout(null)

    peer.add(menuGauche.peer)
    peer.add(grille.peer)
    peer.add(menuBas.peer)

    menuGauche.peer.setBounds(0,0,100,480)
    grille.peer.setBounds(100,0,700,380)
    menuBas.peer.setBounds(100,380,700,100)
  }

  contents = contenuFenetre

  resizable = false
  size = new Dimension(800,480)



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
      grille.repaint()
  }


  /*********************** TIMERS ********************/


  val fps = 60
  val timerAffichage = new Timer(1000/fps, new ActionListener {
    override def actionPerformed(e:java.awt.event.ActionEvent):Unit = {
      grille.repaint()
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
