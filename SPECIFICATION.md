## Objectifs

Ce fichier contient les spécifications pour la partie 3 du projet, notamment ce que l'on veut développer, avec différentes précisions.

### Partie enregistrement (dans un premier temps)

On développe une nouvelle fonctionnalité permettant d'enregistrer ou de charger une partie de TowerDefProj depuis un fichier.
Un fichier peut soit correspondre à une nouvelle partie, soit à une partie déjà commencée et enregistrée pour être reprise plus tard.
Une partie ne pourra être enregistrée qu'entre deux manche (lorsqu'une manche est chargée et est en train de se dérouler, on ne peut pas enregistrer une partie)
On pourra développer un bouton permettant d'enregistrer la partie, un autre pour charger une partie (on pourra utiliser le bouton paramètre qu'on n'avait pas encore implémenté)

### Partie script pour les manches (dans un second temps)

On pourra ensuite développer un langage plus élaboré pour définir le déroulement d'une manche.
En particulier :
* une manche peut se terminer ou non, ou suivant une condition particulière,
* l'apparition d'ennemis peut être déterministe ou aléatoire avec des fréquences d'apparition fixées ou variables pour les différents ennemis,
* des actions particulières peuvent être déclenchées par des évènements (mort d'un ennemi, création d'une tour, etc..)

## Format des fichiers de partie

Les fichiers correspondants à une partie seront enregistrés au format `.game`.
Ils seront enregistrés par défaut dans le répertoire `/src/main/resources/games/`.

Possibilité : les enregistrer sous forme de fichier xml ?

## Informations à enregistrer

Pour savoir quoi enregistrer il suffit de passer en revue les différents objets développés à la fin de la partie 2.

jeu, (ennemis,) tours, effets
* Endommageables présents sur la carte avec leurs caractéristiques
* Etat du gestionnaire de manche : caractéristiques des manches suivantes, en utilisant le langage de description des manches (défini plus bas)

affichage
* zoom, position sur la carte, (position dans l'inventaire)
* inclure un mécanisme de chargement correct des différents composants de la fenêtre (ex : charger inventaire, placer grille, etc..)

test, strategies
* rien