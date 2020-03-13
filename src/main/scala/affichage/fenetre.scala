
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

  /* COMPORTEMENT */

  var mancheEnCours = false

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

  val boutonPlacer = new Button { text = "Placer" } // placer une tour
  val boutonVendre = new Button { text = "Vendre" } // vendre une tour
  val boutonStart = new Button { text = "Démarrer" } // commencer la manche suivante

  val boutons = List(
    boutonPlacer,
    boutonVendre,
    boutonStart
  )
  val inventaire = new InventairePanel(List(TourAttaque))

  val menuGauche = new Panel {
    peer.setLayout(null)

    boutons.foreach(b => peer.add(b.peer))
    peer.add(inventaire.peer)

    var yMax = 0
    def placerBouton(b:Button) = {
      b.peer.setBounds(0,yMax,wBouton,hBouton)
      yMax += hBouton
    }

    boutons.foreach(b => placerBouton(b))

    inventaire.peer.setBounds(0,yMax,wMenuGauche,hFenetre-yMax)
  }

  val zoneMessage = new ZoneMessage
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
  boutons.foreach(b => listenTo(b))

  reactions += {
    case ButtonClicked(b) if b == boutonStart =>
      if (mancheEnCours) {
        zoneMessage.text = "Une manche est en cours !"
      }
      else if (carte.chargerManche) {
        mancheEnCours = true
        zoneMessage.text = "Manche suivante démarrée !"
        timerTick.start
      }
      else {
        zoneMessage.text = "Dernière manche terminée !"
      }

    case ButtonClicked(b) if b == boutonPlacer =>
print("PLACER")
      grille.caseSelect match {
        case None =>
          zoneMessage.text = "Veuillez sélectionner un emplacement."

        case Some(p) =>
          if (carte.tours.exists(
            t => t.pos.isDefined && t.pos.get._1 == p._1 && t.pos.get._2 == p._2)) {
            zoneMessage.text = "Il y a déjà une tour à cet emplacement !"
          }
          else {
            inventaire.caseSelect match {
              case None =>
                zoneMessage.text = "Veuillez sélectionner une tour dans l'inventaire."

              case Some(pi) =>
                if (inventaire.isEltAt(pi)) {
                  val elt = inventaire.getEltAt(pi)
                  if (elt.prix <= carte.argent) {
                    carte.argent -= elt.prix
                    carte.spawnTour(inventaire.getEltAt(pi).creerInstance, p)
                    zoneMessage.text = "Nouvelle tour ajoutée !"
                  }
                  else {
                    zoneMessage.text = "Pas assez d'argent !"
                  }
                }
                else {
                  zoneMessage.text = "La case de l'inventaire est vide !"
                }
            }
          }
      }

    case ButtonClicked(b) if b == boutonVendre =>
print("VENDRE")
      grille.caseSelect match {
        case None =>
          zoneMessage.text = "Veuillez sélectionner un emplacement."

        case Some(p) =>
          if (!carte.tours.exists(t => t.atPosition(p))) {
            zoneMessage.text = "Il n'y a pas de une tour à cet emplacement !"
          }
          else {
            carte.argent += carte.tours.find(t => t.atPosition(p)).get.typeTour.prix
            carte.despawnTour(p)
            zoneMessage.text = "Tour vendue !"
          }
      }
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


  var freqTick = 3
  val timerTick: Timer = new Timer(1000/freqTick, new ActionListener {
    override def actionPerformed(e:java.awt.event.ActionEvent):Unit = {
      carte.tick

      if (carte.manche.finished) {
println("FINISHED")
        mancheEnCours = false
        timerTick.stop()
        zoneMessage.text = "Gagné !"
      }

println("TICK")
    }
  } )


  /* OPÉRATIONS À EFFECTUER À LA FERMETURE DE LA FENÊTRE */

  override def closeOperation() = {
    timerAffichage.stop()
    timerTick.stop()

    System.exit(0)
  }

}
