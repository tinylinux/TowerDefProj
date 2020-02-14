# TowerDefProj

Projet Programmation 2 : Tower Defense

* Développement : Rida LALI, Raphaël LE BIHAN
* Le projet est programmé avec Scala, l'objectif est de réaliser un jeu de Tower Defense.
* Consignes : [page du projet prog 2 19/20](http://www.lsv.fr/~schwoon/enseignement/projet-prog2/2020/index.html)

## Consignes et rendu

### Partie I

Consignes
* [partie1.pdf](https://github.com/tinylinux/TowerDefProj/blob/master/partie1.pdf)

Dates de rendu
* Code : mardi 25 février (23h59)
* Soutenance : vendredi 28 février

## A FAIRE

Général
* Confirmer règles du jeu
* Définir l'arborescence des traits, classes..
* .. et définir l'utilisation de la structure des classes lors du déroulement d'une partie :
comment faire communiquer les objets (ex : temps - synchroniser les ennemis et tours)

Interface graphique
* Dessiner l'interface du jeu
* Apprendre à utiliser Swing
* (chercher sprites)

## Règles du jeu

### Présentation générale

Le jeu présente une carte 2D formant un quadrillage.
Un chemin commence à une extrémité de cette carte et mène à une tour à défendre (la tour principale),
située à l'autre extrémité de la carte.
L'objectif du joueur est de défendre la tour principale d'une invasion ennemie.

Une partie se déroule en une **succession de rounds**, durant lesquels le joueur place des tours sur le quadrillage
formé par la carte, afin d'empêcher la progression des ennemis sur le chemin vers la tour.

Chaque round se déroule en **deux phases** :
* Une phase de placement, où le joueur dispose un nombre limité de tours sur la carte.
* Une phase d'attaque, où des ennemis tentent d'envahir la tour principale par le chemin.

Lorsqu'un ennemi passe à proximité d'une tour, il est pris pour cible par celle ci.
La tour peut alors avoir différents effets, comme infliger des dégâts ou ralentir l'ennemi.

### Idées pour le jeu (on verra ce qu'on fait dans tout ça)

* Le joueur dispose d'un budget, avec lequel il peut acheter des tours lors des phases de placement afin de les placer sur la carte.
Il gagne de l'argent lors des phases d'attaque, lorsque les tours tuent des ennemis. On pourra imaginer qu'il gagne de l'argent supplémentaire lorsqu'il réussit des objectifs particuliers.
* Mettre deux (ou plus) types de tours + ennemis (par exemple bleu et rouge).
Une tour ne peut tirer que sur un ennemi de sa couleur, tirer sur un ennemi de la couleur opposée n'a aucun effet.
* Le jeu se déroule en N rounds de préparation, puis 1 round de mission.
Lors des rounds de préparation, le joueur a le choix d'affronter une armée ennemi parmi plusieurs.
On pourra imaginer différents choix de difficulté ou de types d'ennemis.
Lors du round de mission, le joueur n'a pas le choix de l'armée à affronter.

NB : Les deux idées ci dessus vont assez bien ensemble, car le joueur peut faire le choix de développer une couleur plutôt
qu'une autre, et devra alors bien choisir les contrats à chaque round.


## Implémentation du jeu (et notes)

Le code contiendra plusieurs parties (interdépendantes) :

* Eléments d'une partie : partie, carte, round..
* "Bestiaire" : tours, ennemis, (améliorations)...
* Interface graphique : classes pour la (ou les) fenêtres, boutons..

## Eléments d'une partie

### Partie

Définir comment représenter une partie, avec :

* un "scénario" : quels rounds vont arriver, quels rounds le joueur a-t-il réussi ?
* une situation : une carte (avec des tours placées dessus), de l'argent, des points de vie restants

### Espace

Définir comment représenter :
* carte
* chemin
* tours, ennemis sur la carte

### Temps

L'unité de base du temps pour le moteur de jeu est le tick.
La rapidité des tours et la vitesse des ennemis est représentée par un nombre de ticks entre chaque action.

IL FAUT DIFFÉRENCIER TEMPS EN JEU (TICKS) ET TEMPS RÉEL (SECONDES) !

Un paramètre du nombre de ticks par seconde sera défini, et permettra de régler la vitesse d'affichage du jeu
(affichage normal ou accéléré)

Le temps a un impact sur les déplacements des ennemis, les attaques des tours, et les attaques des ennemis.

## Bestiaire

À voir

## Interface graphique

À voir
