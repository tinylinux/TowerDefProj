# NOTES DE DÉVELOPPEMENT

RESTER ZEN ! :) et voir le projet comme quelque chose d'amusant,
où je peux développer et essayer ce que j'ai envie !

## En train d'être fait


## À faire

* Modifier la représentation des cases de la carte (actuellement : cases(y)(x)...)
* corriger position : le positionnement sur la carte devient un attribut de
  la classe Endommageable
* corriger affichage : les images ne doivent pas être rechargées à chaque fois !!
  (utiliser des val et pas des def)
* améliorer affichage dans l'inventaire
* affichage pathfinding

RAPPEL : ne RIEN ajouter de superflu avant que ce
ne soit le moment de l'ajouter

## Fait

* Pathfinding
* Lecture carte depuis fichier
* Affichage vie de la tour principale
* Définir un système de manches
* Gestion argent
* Ajout placement tour
* Implémenter le diagramme des classes (sans l'argent, ni la fonction
reload_sprite) -> héritage des traits/classes par les différentes classes
filles
* Fusionner les diagrammes/les mettre à jour
* Coder, et ajuster les diagrammes des classes en même temps

### Documentation attaques et déplacement (A mettre)

### Edition de cartes

* Script pour convertir une carte et un scénario (date d'arrivée des différents
  ennemis, etc..) en un fichier texte

### "Sécurité" des objets

* Vérification qu'un ennemi/une tour peut accéder à une position lors du spawn (et lors des déplacements)
* Définir des méthodes non redéfinissables, valeurs et variables privées
* Utiliser des exceptions les cas échéants, si des problèmes arrivent

### Portée, position sur la carte

Endommageable
* carte: Carte

* pv: Int
pvMax: Int

* pos: Option[(Double, Double)]
portee: Double

Si (x, y): (Double, Double) est un point sur la carte,
(x.toInt, y.toInt): (Int, Int) "correspond" au même point que
(x.toInt, y.toInt): (Double, Double)
Il y a donc toujours un décalage de 0.5 à prendre en compte lors des calculs sur
la carte.