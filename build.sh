#!/usr/bin/env bash

mvn clean compile assembly:single
java -jar target/generate-native-config-0.0.1-SNAPSHOT-jar-with-dependencies.jar
native-image -H:-InlineBeforeAnalysis --no-fallback -H:Log=registerResource:3 -H:Class=com.example.generate.NativeApplication -cp target/classes -H:Name=nativeapp
./nativeapp