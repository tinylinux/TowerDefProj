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
    e: Ennemi,
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



  /* Renvoie true si l'endommageable s'est déplacé, false sinon
   * Déplace e vers :
   *  - la case de pos s'ils sont sur des cases différentes
   *  - la même position que pos sinon
   */
  def deplacementPathfinding(
    e: Ennemi,
    pos: (Double, Double),
    accTours: Boolean
      //cherche à éviter les tours sur le chemin ou pas
  ): Boolean = {
    if (e.pos.isDefined && e.vitesse != 0) {
      if (Pos.posToI(e.pos.get) == Pos.posToI(pos)) {
        // e et pos sont sur la même case
        deplacement(e, pos)
        true
      }
      else {
        val (xIE, yIE) = Pos.posToI(e.pos.get)
        val posI = Pos.posToI(pos)
        val acc = {
          if (accTours) SPathfinding.accEnnemi(e.carte)
          else SPathfinding.accEnnemiOsefTours(e.carte)
        }
        SPathfinding.parcoursEnLargeur(
          e.carte.maxX, e.carte.maxY, acc,
          Pos.posToI(pos))(xIE)(yIE) match {
          case None => false
          case Some(c) => {
            deplacement(e, Pos.sPos(
              Pos.iToPos(c), (0.5,0.5) ))
            true
          }
        }
      }
    }
    else { false }
  }



  /* Renvoie true si l'endommageable s'est déplacé, false sinon */
  def deplacementTourPrincipale(
    e: Ennemi
  ): Boolean = {
    if (e.carte.tP.pos.isDefined) {
      deplacementPathfinding(e, e.carte.tP.pos.get, true)
    }
    else { false }
  }


  /* Renvoie true si l'endommageable s'est déplacé, false sinon */
  def deplacementTourPrincipaleOsefTours(
    e: Ennemi
  ): Boolean = {
    if (e.carte.tP.pos.isDefined) {
      deplacementPathfinding(e, e.carte.tP.pos.get, false)
    }
    else { false }
  }


  /* Déplace e vers l'ennemi le plus faible avec des dégâts */
  def deplacementEnnemiPlusFaibleAvecDegats(
    e: Ennemi
  ) = {
    if (e.pos.isDefined && e.vitesse != 0) {
      SCible.plusFaibleAvecDegats(
      e.carte.ennemis.filter(en => en != e)) match {
        case None => ()
        case Some(f:Endommageable) => deplacementPathfinding(e, f.pos.get, true)
      } }
  }


  
}

