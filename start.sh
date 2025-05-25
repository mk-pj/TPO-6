#!/bin/bash

JAR_FILE="target/tpo_6-0.0.1-SNAPSHOT.jar"

if [ ! -f "$JAR_FILE" ]; then
  echo "File $JAR_FILE not found. Did you run 'mvn package'?"
  exit 1
fi

echo "Starting Spring Boot application..."
java -jar "$JAR_FILE"
