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
  var type_nouv: TypeEndommageable
  var type_anc: TypeEndommageable


  /* ATTRIBUTS */

  var prix: Int


  /* METHODES */


}
