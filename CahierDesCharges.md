# Tower Defense : Cahier des charges

## Contraintes

### Fonctionnement

Fonctionnement par round.

Joueur décidant du début d’un round. (via un bouton)

Entre 2 rounds peut :

* Acheter de nouvelles tours

* Placer de nouvelles tours

  Un round se termine quand tous les monstres sont détruits.

### Etat initial

* Plateau : vide
* Nombre de vies du joueur : fixé
* Quantité de monnaie : fixée

### Monstres

Partent d’un point initial jusqu’à l’autre côté.

Un monstre atteint = -1 sur les points de vie + auto-destruction du monstre

A portée des tours, les tours tirent

### Fin du jeu

* Gagné : Le joueur a survécu à tous les rounds
* Perdu : ses points de vie sont à 0

### Grille

* Cases :
  * Vide
  * Tour
  * Monstres (1 ou plusieurs)

## Classes

### Monstres

* Points de vie
* Lenteur (unité de tps nécessaire pour avancer d’une unité de distance)
* Butin que gagne le joueur en le détruisant

### Tours

* Dégâts par tir
* Période des tirs (unité de temps nécessaire entre deux tirs)
* Portée (unité de distance)
* Prix