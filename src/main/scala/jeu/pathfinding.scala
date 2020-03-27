/** TowerDefProj
  * dijkstra.scala
  * Implémentation de l'algorithme de Dijkstra
  */

package jeu

import affichage._

import scala.collection.mutable.Queue
import scala.Array


object Pathfinding {

  /** Renvoie l'itinéraire à suivre en partant de chaque case (non mur) pour aller vers la tour principale */
  def parcoursEnLargeur(
    maxX: Int, maxY: Int,
    cases: Array[Array[Case]],
    posTP: (Int, Int)
  ) = {
    val (xTP, yTP) = posTP

    /* Initialisation du tableau de parcours des cases */
    val casesLues = new Array[Array[Boolean]](maxY)
    for (l <- 0 until casesLues.length) {
      casesLues(l) = new Array[Boolean](maxX)
      for (e <- 0 until casesLues(l).length) {
        casesLues(l)(e) = false
      }
    }

    /* Initialisation des cases des prédécesseurs */
    val pred = new Array[Array[(Int, Int)]](maxY)
    for (l <- 0 until pred.length) {
      pred(l) = new Array[(Int, Int)](maxX)
      for (e <- 0 until pred(l).length) {
        pred(l)(e) = null
      }
    }

    /* Initialisation de la file pour le parcours en largeur */
    val file = new Queue[(Int, Int)]

    /* Ajout de la première case à explorer */
    file.enqueue(posTP)
    casesLues(yTP)(xTP) = true
    pred(yTP)(xTP) = posTP

    /* Parcours en largeur */
    while (!file.isEmpty) {
      /* Défilage de la position d'où continuer l'exploration, MAJ de la file */
      val pos = file.dequeue

      /* Calcul des positions adjacentes à explorer */
      var posAdj: List[(Int, Int)] = List((+1, 0), (-1, 0), (0, +1), (0, -1))
      posAdj.foreach(e => e match { case (dx, dy) => (pos._1 + dx, pos._2 + dy) } )

      def filtre(e: (Int, Int)): Boolean = {
        val (x, y) = e
        0 <= x && x <= maxX && 0 <= y && y <= maxY && (!casesLues(y)(x)) && cases(y)(x).accesEnnemi
      }
      posAdj = posAdj.filter(filtre)

      /* Exploration des cases adjacentes */
      posAdj.foreach(e => {
        val (x, y) = e
        file.enqueue((x, y))
        pred(y)(x) = pos
        casesLues(y)(x) = true
      } )
    }

    /* L'algorithme renvoie le tableau des prédécesseurs dans le parcours en largeur */
    pred
  }
}
