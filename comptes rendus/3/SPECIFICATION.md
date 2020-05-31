
Ce fichier contient les spécifications pour ce qui a été développé dans la partie 3 du projet.

## Enregistrement de partie

On développe une nouvelle fonctionnalité permettant d'enregistrer ou de charger une partie de TowerDefProj depuis un fichier.
Un fichier peut soit correspondre à une nouvelle partie, soit à une partie déjà commencée et enregistrée pour être reprise plus tard.
Une partie ne pourra être enregistrée qu'entre deux manches (lorsqu'une manche est chargée et est en train de se dérouler, on ne peut pas enregistrer une partie)
On pourra développer un bouton permettant d'enregistrer la partie, un autre pour charger une partie (on pourra utiliser le bouton paramètre qu'on n'avait pas encore implémenté)

## Format des fichiers de partie

Les fichiers correspondants à une partie seront enregistrés au format `.tdp`.
Ils seront enregistrés par défaut dans le répertoire `/src/main/resources/saves/`.

## Informations enregistrées

Les informations suivantes sont enregistrées dans un fichier `.tdp` :

* Endommageables présents sur la carte avec leurs caractéristiques (il n'y aura que des tours) ;
* Etat du gestionnaire de manche : liste des manches suivantes et de leurs caractéristiques (apparition d'ennemis, condition de fin de manche) ;
* Zoom, positionnement de la carte

## Organisation du code

On développera le code source destiné à l'enregistrement des parties dans le package `sauvegardes`.
Les différents fichiers seront :

* parser
* chargement
* enregistrement
* io_fichier


