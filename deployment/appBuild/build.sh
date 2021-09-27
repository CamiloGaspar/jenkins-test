#!/bin/bash

# Copia el jar

cp -f ../target/*.jar ./build/

echo "######################"
echo "*** Building image ***"
echo "######################"

cd ./build/ && docker-compose -f docker-compose-build.yml build --no-cache

