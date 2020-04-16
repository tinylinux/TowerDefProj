/** TowerDefProj
  * pathfinding.scala
  */


package strategie
import jeu._
import ennemis._
import tours._

import scala.collection.mutable.Queue
import scala.Array



object SPathfinding {

  /* ALGORITHME DE PATHFINDING */

    /** Renvoie l'itinéraire à suivre en partant de chaque case (accessible) pour aller vers la case cible */
  def parcoursEnLargeur(
    maxX: Int, maxY: Int, // dimensions
    acc: (Int, Int) -> Boolean, // cases accessibles
    cible: (Int, Int) // case à atteindre
  ): Array[Array[Option[(Int, Int)]]] = {
    val (xC, yC) = cible

    /* Initialisation du tableau de parcours des cases */
    val lues = new Array[Array[Boolean]](maxX)
    for (x <- 0 until lues.length-1) {
      lues(x) = new Array[Boolean](maxY)
      for (y <- 0 until lues(x).length-1) {
        lues(x)(y) = false
      }
    }

    /* Initialisation des cases des prédécesseurs */
    val pred = new Array[Array[Option[(Int, Int)]]](maxX)
    for (x <- 0 until pred.length-1) {
      pred(x) = new Array[Option[(Int, Int)]](maxY)
      for (y <- 0 until pred(x).length-1) {
        pred(x)(y) = None
      }
    }

    /* Initialisation de la file pour le parcours en largeur */
    val file = new Queue[(Int, Int)]

    /* Ajout de la première case à explorer
     * (Remarque: osef que celle ci soit accessible ou pas)
     */
    file.enqueue(cible)
    lues(xC)(yC) = true
    pred(xC)(yC) = Some(cible)

    /* Parcours en largeur */
    while (!file.isEmpty) {
      /* Défilage de la position d'où continuer l'exploration, MAJ de la file */
      val pos = file.dequeue

      /* Calcul des positions adjacentes à explorer */
      var posAdj: List[(Int, Int)] = List((+1, 0), (-1, 0), (0, +1), (0, -1))
      posAdj = posAdj.map(e => e match { case (dx, dy) => (pos._1 + dx, pos._2 + dy) } )

      def filtre(e: (Int, Int)): Boolean = {
        val (x, y) = e
        0 <= x && x <= maxX && 0 <= y && y <= maxY && (!lues(y)(x)) && cases(y)(x).accesEnnemi
      }
      posAdj = posAdj.filter(filtre)

      /* Exploration des cases adjacentes */
      posAdj.foreach(e => {
        val (x, y) = e
        file.enqueue((x, y))
        pred(x)(y) = Some(pos)
        lues(x)(y) = true
      } )
    }

    /* L'algorithme renvoie le tableau des prédécesseurs dans le parcours en largeur */
    pred
  }


  def accEnnemi(
    c: Carte
  ): ((Int, Int) -> Boolean) = {
    val tabAcc = new Array[Array[Boolean]](carte.maxX)
    for (x <- 0 until tabAcc.length-1) {
      tabAcc(x) = new Array[Boolean](carte.maxY)
      for (y <- 0 until tabAcc(x).length-1) {
        tabAcc(x)(y) = c.tuiles(x)(y).accesE
      }
    }
    c.tours.foreach(t => {
      if (t.pos.isDefined) {
        val (x, y) = Pos.posToI(t.pos.get)
        tabAcc(x, y) = false
      } } )
      (x, y) => tabAcc(x)(y)
  }


  def accEnnemiOsefTours(
    c: Carte
  ): ((Int, Int) -> Boolean) = {
    val tabAcc = new Array[Array[Boolean]](carte.maxX)
    for (x <- 0 until tabAcc.length-1) {
      tabAcc(x) = new Array[Boolean](carte.maxY)
      for (y <- 0 until tabAcc(x).length-1) {
        tabAcc(x)(y) = c.tuiles(x)(y).accesE
      }
    }
      (x, y) => tabAcc(x)(y)
  }


}
