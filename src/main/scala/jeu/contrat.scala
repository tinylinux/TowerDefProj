/** TowerDefProj
  * contrat.scala
  */


/* PACKAGES */

package jeu
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


abstract class Contrat {

  /* REFERENCES */

  var mag: Magasin
  var typeNouv: TypeEndommageable
  var typeAnc: Option[TypeEndommageable]


  /* ATTRIBUTS */

  var prix: Int


  /* METHODES */

  def action(
    posI: (Int, Int)
  ): Unit
  
}
