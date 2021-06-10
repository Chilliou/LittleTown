# LittleTown

![LittleTown Logo](https://www.iello.fr/sites/default/files/2019-05/couv-article2.png)

## Contexte 

Dans le cadre du projet tutoré de fin semestre 2, ils nous ont été demandés de réalisé un jeu de société du nom de "Littletown" au format console (CUI) et interface graphique (IHM). Il s'agit d'un projet de deux semaines, où à la fin, une soutenance orale se tiendra pour la présentation de celui-ci.

## Règles du jeu

### Bienvenue à Little Town !

Little Town est un jeu familial de stratégie et de placement d'ouvriers. Au-delà des montagnes les plus reculées, il existe encore une région riche et verdoyante encore inexplorée. L'endroit, regorgeant de richesses inexploitées, est idéal pour bâtir la ville de vos rêves. Récoltez des ressources pour ériger vos bâtiments et fondez la plus resplendissante des cités.

### Fondez une ville à votre image !

Simple à prendre en main, Little Town est un jeu destiné à toute la famille. 

Chaque tour se décompose de la manière suivante : 
1. Placez un Ouvrier sur le plateau
2. Récoltez les Ressources environnantes  
3. Activez les bâtiments voisins 
4. Construisez de nouveaux Bâtiments 

Pour l'emporter, placez-vous avec finesse, construisez aux meilleurs endroits et anticipez au mieux les choix de vos adversaires pour tirer profit de toutes les situations et remporter ainsi la victoire. Imposez-vous comme le meilleur architecte !
Mais attention, ne vous concentrez pas uniquement sur la construction. Même si la tentation est forte, **veillez à ne pas dépenser toutes vos ressources**. Vous en aurez besoin pour **nourrir vos ouvriers**.

Après **quatre manches**, le joueur ayant cumulé **le plus de points de victoire** (constructions, argent amassé, objectifs réalisés) sera promu **Grand Bâtisseur**. (© philibertnet.com)

## Un premier aperçu

### Arborescence

> Nous tiendrons compte ici uniquement du répertoire "Code", étant celui qui nous intéresse le plus pour lancer les différentes versions du jeu (CLI & IHM).

![Arborescence](https://i.ibb.co/tXD8Lq6/Capture-d-e-cran-2021-06-10-a-23-40-56.png)

La racine du projet, c'est-à-dire le répertoire "Code", contiendra toutes nos classes utiles au bon fonctionnement de notre jeu. Le "Controleur.java" lancera la version CUI du projet. Ces classes seront réutilisées pour la version IHM depuis le répertoire "IHM". 

## Version interface console (CLI)

Comme dit précédemment, le "Controleur.java", présent dans la racine du projet, permettra de lancer la version CLI du projet qui devrait ressembler à ceci :

![CUI](https://i.ibb.co/4ft1Wc2/Capture-d-e-cran-2021-06-10-a-23-49-48.png)

## Version interface homme-machine (IHM)

La version GUI (IHM), pourra quant à elle être lancé directement depuis le répertoire "IHM" présent dans le répertoire "Code". Ce qui devrait ressembler à ceci :

> Image à venir

## Mise en route

**ATTENTION : Une version de récente Java peut être nécessaire pour le bon fonctionnement du programme**

### Version CLI

1. Ouvrez votre terminal et rendez-vous dans le répertoire "**Code**"
2. Compilez l'ensemble des fichiers Java, en tapant la commande suivante : __javac *.java__
3. Lancez le programme, en tapant la commande suivante : **java Controleur**

### Version IHM

La manipulation reste la même que pour la version CLI, à l'exception que le répertoire change. 

1. Ouvrez votre terminal et rendez-vous dans le répertoire "**Code/IHM**"
2. Compilez l'ensemble des fichiers Java, en tapant la commande suivante : __javac *.java__
3. Lancez le programme, en tapant la commande suivante : **java Controleur**

Manque plus qu'à jouer !

## Crédits

- Équipe 14
- Auteurs : Dorian Lemercier, Théo Lavie, Quentin Savéan, Mathéo Meinerad
