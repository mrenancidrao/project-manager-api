@echo off
echo Iniciando aplicação Spring Boot...

set JAR_FILE=target\project-manager-api-0.0.1-SNAPSHOT.jar

if exist %JAR_FILE% (
    java -jar %JAR_FILE%
) else (
    echo Arquivo JAR não encontrado em %JAR_FILE%
    echo Execute "mvn clean install" antes de rodar este script.
    exit /b 1
)