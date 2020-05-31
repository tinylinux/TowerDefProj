/** TowerDefProj
  * c_bpause.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._
import sauvegardes._


object CBCharger {

  def clic: Unit = {
    println("CHARGER")
    if (FileChooserJeu.showOpenDialog(FenetreJeu.peer) == FileChooserJeu.APPROVE_OPTION) {
      val file = FileChooserJeu.getSelectedFile()
      Charger.charger(file)
    }
  }

}
