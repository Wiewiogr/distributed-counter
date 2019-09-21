#!/usr/bin/env bash

docker kill $(docker ps -q | sort -R | head -n1)
sleep 5
docker-compose up -d