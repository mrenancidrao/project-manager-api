#!/bin/bash

echo "Iniciando aplicação..."

JAR_FILE="target/project-manager-api-0.0.1-SNAPSHOT.jar"

if [ -f "$JAR_FILE" ]; then
    java -jar "$JAR_FILE"
else
    echo "Arquivo JAR não encontrado em $JAR_FILE"
    echo "Execute 'mvn clean install' antes de rodar este script."
    exit 1
fi