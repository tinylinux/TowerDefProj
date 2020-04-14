/** TowerDefProj
  * boutons_jeu.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu._

import scala.swing._


object BPause
    extends BLogo {
  var nom: String = "icones/pause.jpg"
  var l: Int = DimJeu.lBJeu

  ZoneBoutons.peer.add(this.peer)
  this.peer.setBounds(
    (DimJeu.wZoneBoutons-4*DimJeu.lBJeu)/5,
    (DimJeu.hZoneBoutons-DimJeu.lBJeu)/2,
    DimJeu.lBJeu,DimJeu.lBJeu)
}

object BSlow
    extends BLogo {
  var nom: String = "icones/slow.jpg"
  var l: Int = DimJeu.lBJeu

  ZoneBoutons.peer.add(this.peer)
  this.peer.setBounds(
    (DimJeu.wZoneBoutons-4*DimJeu.lBJeu)*2/5 + DimJeu.lBJeu,
    (DimJeu.hZoneBoutons-DimJeu.lBJeu)/2,
    DimJeu.lBJeu,DimJeu.lBJeu)
}

object BFast
    extends BLogo {
  var nom: String = "icones/fast.jpg"
  var l: Int = DimJeu.lBJeu

  ZoneBoutons.peer.add(this.peer)
  this.peer.setBounds(
    (DimJeu.wZoneBoutons-4*DimJeu.lBJeu)*3/5 + DimJeu.lBJeu*2,
    (DimJeu.hZoneBoutons-DimJeu.lBJeu)/2,
    DimJeu.lBJeu,DimJeu.lBJeu)
}

object BParam
    extends BLogo {
  var nom: String = "icones/param.png"
  var l: Int = DimJeu.lBJeu

  ZoneBoutons.peer.add(this.peer)
  this.peer.setBounds(
    (DimJeu.wZoneBoutons-4*DimJeu.lBJeu)*4/5 + DimJeu.lBJeu*3,
    (DimJeu.hZoneBoutons-DimJeu.lBJeu)/2,
    DimJeu.lBJeu,DimJeu.lBJeu)
}
