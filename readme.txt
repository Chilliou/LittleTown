
                      _                 __  _  _   
                     (_)               /_ || || |  
   ___   __ _  _   _  _  _ __    ___    | || || |_ 
  / _ \ / _` || | | || || '_ \  / _ \   | ||__   _|
 |  __/| (_| || |_| || || |_) ||  __/   | |   | |  
  \___| \__, | \__,_||_|| .__/  \___|   |_|   |_|  
           | |          | |                        
           |_|          |_|                        


========================
|       README         |
========================
____________________________________
/          DESCRIPTION             /

Jeu de société Little Town développé au format numérique.

____________________________________
/    EXÉCUTER LE PROGRAMME        /


/!\ Avant d'exécuter le programme assurez-vous d'avoir une version récente de Java (nous avons
ici utilisé la version 14). /!\


- Étape 1 : Compiler l'ensemble des fichiers .java

Compiler l'ensemble des fichiers java à l'aide votre terminal en lançant la commande suivante
depuis le répertoire "Code" du projet :

//Permet de compiler le programme à l'aide des paquetages.
javac @compile.list -d /home/TP/paquetage_class/paquetages


- Étape 2 : Lancer le programme (Scénario normal)

Une fois l'ensemble des fichiers Java compilé, exécuter la commande suivante à partir du répertoire "cui" afin de lancer le programme :

cd cui

java littletown.cui.Controleur


____________________________________
/           SCENARIOS              /

( à lancer depuis le répertoire cui )

> Scénario banque vide:

java littletown.cui.Controleur bv


> Scénario nourrir ouvrier:

java littletown.cui.Controleur no

> Scénario milieu de partie:

java littletown.cui.Controleur mp


> Scénario fin de partie  :

java littletown.cui.Controleur fp

