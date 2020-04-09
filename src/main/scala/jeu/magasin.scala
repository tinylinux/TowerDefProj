/** TowerDefProj
  * magasin.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


abstract class Magasin {

  /* REFERENCES */

  var partie: Partie
  var contrats: List[Contrat]

  
  /* METHODES */

  def commande(
    idContrat: Int,
    posI: (Int, Int)
  ): Boolean =
    OMagasin.commande(this, idContrat, posI)

  def getContrat(
    idContrat: Int
  ): Option[Contrat] =
    OMagasin.getContrat(this, idContrat)

}
