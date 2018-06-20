# Tracking

## Pré-requis

* maven v3+
* java v1.8+
* docker

## Installation

* instancier les conteneurs docker pour MySql et RabbitMQ avec la commande `docker-compose up`
* construire le .jar executable avec la commande `mvn clean install`
* executer la commande `java -jar target/back-suivi-0.0.1.jar`


## Usage

se rendre à l'adresse `http://localhost:8080`

* Pour créer un tracking : `POST` sur l'url `/tracking` avec la payload suivant :
>* userId (string)
>* productId (string)
>* name (string)

* Pour récupérer un tracking et ses steps: `GET` sur l'url `/tracking/:id`

* Pour créer un step : `POST` sur l'url `/tracking/:id/step` avec la payload suivant :
 >* lieu (string)
 >* etat (long)
 
 `etat` peut prendre comme valeurs `1` ("en attente"), `2` ("en cours"), `3` ("livrer"),


## Rabbit MQ

une fois RAbbit MQ instancié via Docker :
* se rendre à l'adresse `http://localhost:15672`
* se connecter avec les identifiants `guest:guest`