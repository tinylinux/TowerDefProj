# Langage utilisé dans les fichiers `.tdp`

## Structure générale

Un fichier `.tdp` contient les différentes déclararations qui peuvent être des groupements de déclarations (qu'on appellera bloc) ou des champs qui contiennent un nom de champ et une valeur.

Les noms des blocs sont donnés en majuscule, suivis de leur contenu entre accolades.
Les noms des champs sont donnés en minuscule (avec majuscule comme séparation des différents mots), et sont renseignés sous la forme `champ: valeur`.

La structure d'un ficier `.tdp` est constituée des blocs suivants donnés dans cet ordre :

* Un bloc `CARTE` contenant les informations sur la carte,
* Un ou plusieurs blocs `TOUR` dont un bloc `TOUR PRINCIPALE` contenant les informations sur chacunes des tours sur la carte
* Un ou plusieurs blocs `MANCHE` décrivant le contenu des différentes manches
* Un bloc `AFFICHAGE` optionnel décrivant les paramètres d'affichage de la grille.

Les détails de chaque bloc sont donnés plus bas.
Un fichier exemple est donné : `src/main/resources/saves/partie.tdp`.


## Bloc `CARTE`

Le bloc `CARTE` contient les champs suivants :

* `maxX` et `maxY` correspondant aux dimensions de la carte
* le bloc `TUILES`

Le bloc `TUILES` contient différentes chaines de caractères séparés par des blancs.
Chaque caractère correspont à une tuile sur la carte.
Chaque chaine doit avoir autant de caractères que la valeur de `maxX` et il doit y avoir autant de chaines que la valeur de `maxY`.
Les différents caractères possibles sont :

* `O` pour une tuile Sol
* `X` pour une tuile Mur
* `S` pour une tuile Spawn


## Blocs `TOUR [PRINCIPALE]`

Parmis ces blocs le premier doit être un bloc `TOUR PRINCIPALE`, et les autres des blocs `TOUR`. La tour correspondante au bloc `TOUR PRINCIPALE` sera la tour principale de la carte.

Les champs indiqués correspondent aux attributs que possède une Tour dans le jeu.
Seul certains champs sont modifiés ou rajoutés.
On précisera les types attendus pour les différentes valeurs : (int), (double), un tuple des types précédents, ou autre.
De plus certains champs sont optionnels (ils seront décrits entre crochets []), s'ils ne sont pas renseignés les valeurs par défaut sont utilisées.

Les différents champs sont :

* `tour` où la valeur est le nom de la classe utilisée pour la tour (par exemple `Defenseuse` ou `Sniper`)
* `x` et `y` (int) qui sont les coordonnées de la tour
* `[pvMax]`, `[pv]`, `[cooldownAct]`, `[cooldown]`, `[deg]`, `[soin]` (int)
* `[portee]`, `[rayon]` (double)


## Bloc `MANCHE`

Les blocs `MANCHE` décrivent le contenu des différentes manches dans la partie.
Les manches seront jouées dans l'ordre où elles apparaissent dans le fichier `.tdp`.

Un bloc `MANCHE` contient aucun, un ou plusieurs blocs `SPAWN` suivis d'un bloc `FIN`. Ces deux blocs sont renseignés ci-dessous.

### Bloc `SPAWN`

Un bloc `SPAWN` contient les champs suivants :

* `ennemi` qui est le nom de la classe de l'ennemi concerné
* `pos` ou (`x` et `y`).
  Si le champ `pos` (double)*(double) est renseigné, l'ennemi apparaitra exactement à l'emplacement correspondant.
  Si les champs `x` et `y` (int) sont fournis, l'ennemi apparaîtra à un emplacement aléatoire sur la case aux coordonnées indiquées.
* `tick`, `per` ou `aleat`
  Si le champ `tick` (int) est renseigné, un seul ennemi apparaîtra après qu'autant de ticks se soient écoulés depuis le début de la manche que la valeur indiquée.
  Si le champ `per` (int) est renseigné, l'ennemi apparaîtra périodiquement, la période étant la valeur indiquée.
  Si le champ `aleat` (float) est renseigné, à chaque tick un ennemi pourra apparaitre avec la probabilité correspondant à la valeur indiquée.

Un bloc `FIN` contient seulement le champ `timeout` (int).
Après que autant de ticks se soient écoulés que la valeur indiquée, les spawns s'arrêtent pour cette manche, et la manche se termine lorsque tous les ennemis ont été éliminés.


## Bloc `AFFICHAGE`

Le bloc `AFFICHAGE` ne contient que deux champs : `offset` (int)*(int) et `zoom` (int) correspondants aux paramètres d'affichage de la grille.


