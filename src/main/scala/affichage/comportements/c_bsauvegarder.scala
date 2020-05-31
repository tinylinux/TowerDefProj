/** TowerDefProj
  * c_bparam.scala
  */



/* PACKAGES */

package affichage.comportements

import affichage.composants._
import jeu._
import sauvegardes._


object CBSauvegarder {

  def clic: Unit = {
    println("SAUVEGARDER")
    if (FileChooserJeu.showSaveDialog(FenetreJeu.peer) == FileChooserJeu.APPROVE_OPTION) {
      val file = FileChooserJeu.getSelectedFile()
      Enregistrer.enregistrer(file)
    }
  }

}
