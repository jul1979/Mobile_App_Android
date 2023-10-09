# Projet MOBG5

Ce dépôt contient les sources du projet **BrewUS**.

## Description

L'application permet de trouver des brasseries  en encodant des critères de recherche comme la ville,la disponibilité d'une terrasse(patio),l'acceptation d'animaux de compagnie etc.
Les données disponibles sont limitées aux USA.L'application permet  aux utilisateurs possédant 
un compte d'ajouter des revues pour une brasserie indépendamment de sa localisation géographique , de voir les revues d'une brasserie sélectionnée.  

## Persistance des données & Backend

L'application conserve un historique des revues des brasseries. Cet historique est persisté  via firebase.

Les données relatives aux comptes utilisateurs, aux revues des utilisateurs sont persistées via firebase :[Firebase URL ](https://brewery-cb13b-default-rtdb.firebaseio.com)



## Service rest

Pour collecter les données relatives aux brasseries, des appels au service rest suivant sont effectués : [Données Service Rest](https://www.openbrewerydb.org/)


## Auteur

**Jules Ruzindana** g48982
