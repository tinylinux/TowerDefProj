/** TowerDefProj
  * partie_obj.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


object OPartie {

  /* METHODES */

  def tick(
    p: Partie
  ): Unit = {
    if ((!p.gagne.isDefined) && p.gM.mEnCours.isDefined) {
      p.gM.mEnCours.tick
      p.carte.tick
      p.actuFin
    }
  }


  def actuFin(
    p: Partie
  ): Unit = {
    if (!p.gagne.isDefined) { // la partie n'est pas déjà terminée
      if (p.gM.mEnCours.isDefined) { // une manche est chargée
        if (p.gM.mEnCours.condFin.isDefined) { // cette manche est terminée
          if (p.gM.mEnCours.condFin.get) { // cette manche est gagnée
            p.gM.mEnCours = None // on la retire
            if (p.gM.m.isEmpty) { // c'était la dernière manche
              p.gagne = Some(true) // la partie est gagnée
            }
          }
          else { // cette manche est perdue
            p.gagne = Some(false) // la partie est perdue
          }
        }
      }
      else { // aucune manche n'est chargée
        if (p.gM.m.isEmpty) { // et il n'en reste aucune autre
          p.gagne = Some(true) // la partie est gagnée
        }
      }
    }
  }


  // vérifie que le contrat existe
  def acheter(
    p: Partie,
    idContrat: Int,
    posI: (Int, Int)
  ): Unit = {
    p.mag.getContrat(idContrat) match {
      case None => () // le contrat n'existe pas
      case Some(c) => { // le contrat existe
        if (p.argent >= c.prix) { // le joueur a assez d'argent
          if (p.mag.commande(idContrat, posI)) { // l'achat a réussi
            p.argent -= c.prix // le prix est déduit de l'argent
      } } } }
  }




}
