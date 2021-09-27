#!/bin/bash

# Copia el jar

cp -f ./target/*.jar ./deployment/appBuild/

echo "######################"
echo "*** Building image ***"
echo "######################"

cd ./deployment/appBuild/ && docker build -t appgate-calculator:$BUILD_TAG .

