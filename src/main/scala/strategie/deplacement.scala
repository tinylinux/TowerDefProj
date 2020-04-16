/** TowerDefProj
  * deplacement.scala
  */


package strategie
import jeu._
import ennemis._
import tours._


object SDeplacement {

  /* STRATEGIES */

  def deplacement(
    e: Endommageable,
    pos: (Double, Double)
  ) = {
    if (e.vitesse != 0 && e.pos.isDefined) {
      val posE = e.pos.get
      if (Pos.dist(posE, pos) <= e.vitesse) {
        // e peut atteindre pos en un tick
        e.carte.moveE(e, pos)
      }
      else {
        // e se déplace le plus possible vers pos en un tick
        val depl = Pos.mPos(
          Pos.vect(posE, pos),
          Pos.dist(posE, pos)*e.vitesse
        ) // vecteur de déplacement
        e.carte.moveE(e, Pos.sPos(posE, depl))
      }
    }
  }


  /* Renvoie true si l'endommageable s'est déplacé, false sinon */
  def deplacementTourPrincipale(
    e: Endommageable
  ): Boolean = {
    if (e.pos.isDefined && e.vitesse != 0 && e.carte.tP.pos.isDefined) {
      val (xIE, yIE) = Pos.posToI(e.pos.get)
      SPathfinding.parcoursEnLargeur(
        e.carte.maxX, e.carte.maxY,
        SPathfinding.accEnnemi(e.carte),
        Pos.posToI(e.carte.tP.pos.get))(xIE)(yIE) match {
        case None => false
        case Some(c) => {
          deplacement(e, Pos.sPos(
            Pos.posToI(c), (0.5,0.5) ))
          true
        }
      }
    }
    else { false }
  }


  /* Renvoie true si l'endommageable s'est déplacé, false sinon */
  def deplacementTourPrincipaleOsefTours(
    e: Endommageable
  ): Boolean = {
    if (e.pos.isDefined && e.vitesse != 0 && e.carte.tP.pos.isDefined) {
      val (xIE, yIE) = Pos.posToI(e.pos.get)
      SPathfinding.parcoursEnLargeur(
        e.carte.maxX, e.carte.maxY,
        SPathfinding.accEnnemiOsefTours(e.carte),
        Pos.posToI(e.carte.tP.pos.get))(xIE)(yIE) match {
        case None => false
        case Some(c) => {
          deplacement(e, Pos.sPos(
            Pos.posToI(c), (0.5,0.5) ))
          true
        }
      }
    }
    else { false }
  }

  
}
