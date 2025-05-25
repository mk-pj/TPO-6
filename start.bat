@echo off
set JAR_FILE=target\tpo_6-0.0.1-SNAPSHOT.jar

if not exist %JAR_FILE% (
    echo [ERROR] %JAR_FILE% not found. Did you run 'mvn package'?
    exit /b 1
)

echo [OK] Starting Spring Boot application...
java -jar %JAR_FILE%
