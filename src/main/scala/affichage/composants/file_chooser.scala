/** TowerDefProj
  * file_chooser.scala
  */



package affichage.composants

import javax.swing._
import java.io._


object FileChooserJeu extends JFileChooser {

  val APPROVE_OPTION = JFileChooser.APPROVE_OPTION

  setCurrentDirectory(new File("src/main/resources/saves"))

}
