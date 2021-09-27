#!/bin/bash

# Copia el jar

cp -f ./target/*.jar ./deployment/appBuild/

echo "######################"
echo "*** Building image ***"
echo "######################"

cd ./deployment/appBuild/ && docker-compose -f docker-compose-build.yml build --no-cache

