# Tracking

## Pré-requis

* maven v3+
* java v1.8+
* mysql v5.7+

## Installation

* copier le fichier `src/main/resources/application.properties.example` en `src/main/resources/application.properties` et y renseigner les accès à la BDD
* construire le .jar executable avec la commande `mvn clean install`

## Execution

* executer la commande `java -jar target/back-suivi-0.0.1.jar`

## Docker

Docker est utilisé pour instancier une image de RabbitMQ.

Pour utiliser RabbitMQ :
* executer la commande `docker-compose up`
* se rendre à l'adresse `http://localhost:15672`
* se connecter avec les identifiants `guest:guest`