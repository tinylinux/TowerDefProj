/** TowerDefProj
  * boutons_jeu.scala
  */



/* PACKAGES */

package affichage.composants

import affichage.comportements._
import jeu

import scala.swing._


object BPause
    extends BLogo {
  ZoneBoutons.peer.add(this.peer)
  this.peer.setBounds(
    (wZoneBoutons-4*lBJeu)/5,
    (hZoneBoutons-lBJeu)/2,
    lBJeu,lBJeu)
}

object BSlow
    extends BLogo {
  ZoneBoutons.peer.add(this.peer)
  this.peer.setBounds(
    (wZoneBoutons-4*lBJeu)*2/5 + lBJeu,
    (hZoneBoutons-lBJeu)/2,
    lBJeu,lBJeu)
}

object BFast
    extends BLogo {
  ZoneBoutons.peer.add(this.peer)
  this.peer.setBounds(
    (wZoneBoutons-4*lBJeu)*3/5 + lBJeu*2,
    (hZoneBoutons-lBJeu)/2,
    lBJeu,lBJeu)
}

object BParam
    extends BLogo {
  ZoneBoutons.peer.add(this.peer)
  this.peer.setBounds(
    (wZoneBoutons-4*lBJeu)*4/5 + lBJeu*3,
    (hZoneBoutons-lBJeu)/2,
    lBJeu,lBJeu)
}
