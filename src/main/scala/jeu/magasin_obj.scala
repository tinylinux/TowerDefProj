/** TowerDefProj
  * magasin_obj.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


object OMagasin {

  /* METHODES */

  // vérifie que le contrat existe
  def getContrat(
    m: Magasin,
    i: Int // indice du contrat
  ): Option[Contrat] =
    try Some(m.contrats(i))
    catch { case _ : Throwable => None }


  // vérifie que le contrat existe
  // et que la position existe
  def commande(
    m: Magasin,
    idContrat: Int,
    posI: (Int, Int)
  ): Boolean = {
    if (m.partie.carte.posISurCarte(posI)) { // la position existe
      m.getContrat(idContrat) match {
        case None => false // le contrat n'existe pas
        case Some(c) => { // le contrat existe
          m.partie.carte.getTourAt(posI) match {
            case None => { // pas de tour à la position
              if (!c.typeAnc.isDefined) { // pas de tour prévue
                c.action(posI) // action du contrat
                true
              }
              else { false } // tour prévue
            }
            case Some(t:Tour) => { // il y a une tour à la position
              if (c.typeAnc.isDefined
                && c.typeAnc.get == t.typeE) {
                // tour prévue et types correspondants
                m.partie.carte.despawnT(t) // despawn de la tour
                c.action(posI) // action du contrat
                true
              }
              else { false }
              // pas de tour prévue, ou mauvais type
            } } } } }
    else { false } // la position n'existe pas
  }


}
