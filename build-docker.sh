#!/usr/bin/bash

./gradlew build;

docker build -t transportbooking-docker .;
