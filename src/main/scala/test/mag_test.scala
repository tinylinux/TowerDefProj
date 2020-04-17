/** TowerDefProj
  * mag_test.scala
  */


/* PACKAGES */

package test
import jeu._
import affichage._
import strategie._
import tours._
import ennemis._
import effets._


object MagTest
    extends Magasin {

  /* REFERENCES */

  var partie: Partie = PartieTest
  var contrats: List[Contrat] = Nil

  var c =
    new Contrat {
      var mag: Magasin = MagTest
      var typeNouv: TypeEndommageable = TypeDefenseuse
      var typeAnc: Option[TypeEndommageable] = None
      var prix: Int = 50
      def action(posI: (Int, Int)): Unit = {
        mag.partie.carte.spawnT(new Defenseuse, posI)
      }
    }

}
