#!/usr/bin/env bash

mvn clean compile assembly:single
java -jar target/generate-native-config-0.0.1-SNAPSHOT-jar-with-dependencies.jar
mvn -Pnative package
./target/nativeapp