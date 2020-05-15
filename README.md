# TowerDefProj

Projet Programmation 2 : Tower Defense

* Développement : Rida LALI, Raphaël LE BIHAN
* Le projet est programmé avec Scala, l'objectif est de réaliser un jeu de Tower Defense.
* Consignes : [page du projet prog 2 19/20](http://www.lsv.fr/~schwoon/enseignement/projet-prog2/2020/index.html)

## Consignes

* [partie1.pdf](https://github.com/tinylinux/TowerDefProj/blob/master/consignes/partie1.pdf)
* [partie2.pdf](https://github.com/tinylinux/TowerDefProj/blob/master/consignes/partie2.pdf)
* [partie3.pdf](https://github.com/tinylinux/TowerDefProj/blob/master/consignes/partie3.pdf)

## Présentation du jeu

### Objectif

Le jeu présente une carte 2D formant un labyrinthe en quadrillage.
Sur ce labyrinthe se trouvent une tour à défendre (la tour principale), et des points d'apparitions pour les ennemis.
L'objectif du joueur est de défendre la tour principale d'une invasion ennemie.

### Déroulement d'une partie

Une partie se déroule en une **succession de manches**, durant lesquels le joueur place des tours sur le quadrillage
formé par la carte, afin d'empêcher la progression des ennemis sur le chemin vers la tour.
Lorsqu'un ennemi passe à proximité d'une tour, il est pris pour cible par celle ci, et peut lui aussi l'attaquer.

Chaque round se déroule en **deux phases** :
* Une phase de placement, où le joueur dispose un nombre limité de tours sur la carte.
* Une phase d'attaque, où des ennemis tentent d'envahir la tour principale par le chemin.

Le joueur dispose d'une somme d'argent afin d'acheter de nouvelles tours ou faire évoluer celles présentes sur la carte.
Il gagne de l'argent à la fin de chaque manche, qu'il peut dépenser pendant la phase de placement suivante.