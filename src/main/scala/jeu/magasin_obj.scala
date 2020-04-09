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


object Magasin {

  /* METHODES */

  // vérifie que le contrat existe
  def getContrat(
    m: Magasin,
    idContrat: Int
  ): Option[Contrat] = {
    var i = idContrat
    var l = m.contrats
    while (i > 0) { l match {
      case [] => i = 0
      case e :: l2 => l = l2
    } }
    l.headOption
  }


  // vérifie que le contrat existe
  // et que la position existe
  def commande(
    m: Magasin,
    idContrat: Int,
    posI: (Int, Int)
  ): Boolean = {
    if (m.carte.posISurCarte(posI)) { // la position existe
      m.getContrat(idContrat) match {
        case None => () // le contrat n'existe pas
        case Some(c) => { // le contrat existe
          m.getTourAt(posI) match {
            case None => { // pas de tour à la position
              if (!c.typeAnc.isDefined) { // pas de tour prévue
                m.carte.spawnT( // on ajoute la tour commandée
                  c.typeNouv.instance
                ) } }
            case Some(t) => { // il y a une tour à la position
              if (c.typeAnc.isDefined) { // tour prévue
                if (c.typeAnc.get == t.typeE) { // types correspondants
                  m.carte.despawnT(t)
                  m.carte.spawnT(
                    c.typeNouv.instance
                  ) } } } } } } }
  }


}
