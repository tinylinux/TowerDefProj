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
  var contrats: List[Contrat] =
    List(
      new Contrat { // DEFENSEUSE
        var mag: Magasin = MagTest
        var typeNouv: TypeEndommageable = TypeDefenseuse
        var typeAnc: Option[TypeEndommageable] = None
        var prix: Int = 50
        def action(posI: (Int, Int)): Unit = {
          mag.partie.carte.spawnT(new Defenseuse, posI)
        }
      },
      new Contrat { // MORTIER
        var mag: Magasin = MagTest
        var typeNouv: TypeEndommageable = TypeMortier
        var typeAnc: Option[TypeEndommageable] = Some(TypeDefenseuse)
        var prix: Int = 30
        def action(posI: (Int, Int)): Unit = {
          mag.partie.carte.spawnT(new Mortier, posI)
        }
      },
      new Contrat { // SNIPER
        var mag: Magasin = MagTest
        var typeNouv: TypeEndommageable = TypeSniper
        var typeAnc: Option[TypeEndommageable] = Some(TypeDefenseuse)
        var prix: Int = 50
        def action(posI: (Int, Int)): Unit = {
          mag.partie.carte.spawnT(new Sniper, posI)
        }
      },
      new Contrat { // LAMPADAIRE
        var mag: Magasin = MagTest
        var typeNouv: TypeEndommageable = TypeLampadaire
        var typeAnc: Option[TypeEndommageable] = None
        var prix: Int = 50
        def action(posI: (Int, Int)): Unit = {
          mag.partie.carte.spawnT(new Lampadaire, posI)
        }
      },
      new Contrat { // YOGI
        var mag: Magasin = MagTest
        var typeNouv: TypeEndommageable = TypeYogi
        var typeAnc: Option[TypeEndommageable] = Some(TypeLampadaire)
        var prix: Int = 80
        def action(posI: (Int, Int)): Unit = {
          mag.partie.carte.spawnT(new Yogi, posI)
        }
      },
      new Contrat { // GLUANT
        var mag: Magasin = MagTest
        var typeNouv: TypeEndommageable = TypeGluant
        var typeAnc: Option[TypeEndommageable] = Some(TypeLampadaire)
        var prix: Int = 10
        def action(posI: (Int, Int)): Unit = {
          mag.partie.carte.spawnT(new Gluant, posI)
        }
      },
      new Contrat { // BARRIERE
        var mag: Magasin = MagTest
        var typeNouv: TypeEndommageable = TypeBarriere
        var typeAnc: Option[TypeEndommageable] = None
        var prix: Int = 50
        def action(posI: (Int, Int)): Unit = {
          mag.partie.carte.spawnT(new Barriere, posI)
        }
      },
    )

}
