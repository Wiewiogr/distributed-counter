#!/usr/bin/env bash

docker-compose down
mvn clean package
docker-compose up --build