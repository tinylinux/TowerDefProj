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

// TEST !!!
println(casesLues(0)(0).toString)

    /* Initialisation des cases des prédécesseurs */
    val pred = new Array[Array[(Int, Int)]](maxY)
    for (l <- 0 until pred.length) {
      pred(l) = new Array[Boolean](maxX)
      for (e <- 0 until pred(l).length) {
        pred(l)(e) = false
      }
    }

    /* Initialisation de la file pour le parcours en largeur */
    val file = new Queue[(Int, Int)]

    /* Ajout de la première case à explorer */
    file.enqueue(posTP)
    casesLues(xTP)(yTP) = true
    pred(xTP)(yTP) = posTP

    /* Parcours en largeur */
    while (!file.isEmpty) {
      /* Défilage de la position d'où continuer l'exploration, MAJ de la file */
      val pos = file.dequeue

      /* Calcul des positions adjacentes à explorer */
      var posAdj: List[(Int, Int)] = List((+1, 0), (-1, 0), (0, +1), (0, -1))
      posAdj.foreach((dx, dy) =>
        (pos + dx, pos + dy) )
      posAdj = posAdj.filter((x, y) =>
        0 <= x && x <= maxX && 0 <= y && y <= maxY )
      posAdj = posAdj.filter((x, y) =>
        (!casesLues(x)(y)) && cases(x)(y).accesEnnemi )

      /* Exploration des cases adjacentes */
      posAdj.foreach((x, y) => {
        file.enqueue((x, y))
        pred(x)(y) = pos
        casesLues(x)(y) = true
      } )
    }

    /* L'algorithme renvoie le tableau des prédécesseurs dans le parcours en largeur */
    pred
  }
}
