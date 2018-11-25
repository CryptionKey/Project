# Programme de gestion d'un restaurant de Bagel

## Cadre :
Etudes

## Lien des consignes :
https://github.com/ledoyen/tp-java/tree/master/projet/3A_2018

## Temps de développement:
Octobre-Novembre 2018

## Langages utilisés:
Java, bash, markdown

## IDE utilisé :
IntelliJ Idea

## Fonctionnalités:
- afficher la liste des opérations disponibles
- ajouter un produit à la vente (nom, prix, stock)
- afficher la liste des produits à la vente (ainsi que le nombre restant)
- ouvrir la note d'un client (il peut y avoir plusieurs clients dans le restaurant au même moment)
- enregistrer la vente d'un produit sur la note d'un client
- clôturer la note d'un client en affichant :
	- le prix de chaque produit HT (hors-taxe)
	- le prix total HT
	- la TVA (10%)
	- le prix TTC
- afficher les données comptables:
	- total des rentrées d'argent
	- total de la TVA facturée
- quitter
- retrait automatique de la liste des produits en vente d'un produit en rupture de stock
- possibilité d'offrir une remise de 10% au moment de la clôture d'une note

## Difficultés rencontrées:
### __Gestion de multiples cas:__
- Vérifier qu'un client existe
- Vérifier qu'un produit soit en vente
- Vérifier qu'il y en a assez par rapport à la quantité demandée par un client
- Vérifier si un client reprend d'un même produit, auquel cas il faut incrémenter la quantité du produit déjà présent sur sa note
**Résolution :** création de plusieurs méthodes de vérification pour traiter ces différents cas

### __Difficultés à respecter le nombre maximal de lignes autorisées pour les méthodes et les classes__
**Résolution :** cela nous a contraint à penser "orienté objet" et à créer plus de méthodes afin de diviser le code en plusieurs actions élémentaires

### __Transmission des avancées de chacun__<br/>
**Résolution :** Utilisation de GitHub qui nous a fait économiser beaucoup de temps, notamment pour fusionner nos versions du projet