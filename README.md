# Tracking

## Pré-requis

* maven v3+
* java v1.8+
* docker

## Installation

* instancier les conteneurs docker pour MySql et RabbitMQ avec la commande `docker-compose up`
* construire le .jar executable avec la commande `mvn clean install`


## Execution

* executer la commande `java -jar target/back-suivi-0.0.1.jar`

## Rabbit MQ

une fois RAbbit MQ instancié via Docker :
* se rendre à l'adresse `http://localhost:15672`
* se connecter avec les identifiants `guest:guest`