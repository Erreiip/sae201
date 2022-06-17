# Réseau de cuves - Projet de SAE 2.01/2.02

## Groupe N°1
- B1 - Tran Thai Duc DINH
- B2 - TAUVEL Nolan
- A1 - GASCOIN Romain
- D2 - LANDRIN Hugo
- A2 - LE MEUR Pierre

## Utilisation
Vous devrez compiler tous les fichiers Java contenus dans le dossier "src".  
Vous pourrez faire cela en vous plaçant dans le dossier source et  
```shell
javac src\app1\ControleurApp1.java
javac src\app2\ControleurApp2.java
javac src\common\Tests.java
```
`javac` va aussi compiler les fichiers Java qui sont utilisés par les classes initialement compilées, donc nous 
n'avons pas à nous soucier de tous les autres fichiers.

- Pour utiliser l'application 1 en mode CUI, utilisez `java src\app1\ControleurApp1`
- Pour utiliser l'application 1 en mode GUI, utilisez `java src\app1\ControleurApp1 gui`
- Pour utiliser l'application 2, utilisez `java src\app1\ControleurApp2`
- Pour utiliser les tests unitaires, utilisez `java src\common\Tests`