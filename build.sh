#!/bin/bash

cd service
source ./env-variable.sh
mvn clean package
cd ..
cd MovieCruiserAuthenticationService
source ./env-variable.sh
mvn clean package
cd ..
