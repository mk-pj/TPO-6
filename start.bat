@echo off
set JAR_FILE=target\TPO6_KM_S31209-0.0.1-SNAPSHOT.jar

if not exist %JAR_FILE% (
    echo [ERROR] %JAR_FILE% not found. Did you run 'mvn package'?
    exit /b 1
)

echo [OK] Starting Spring Boot application...
java -jar %JAR_FILE%
