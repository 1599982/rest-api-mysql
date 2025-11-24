@echo off

REM Configuración por defecto
set JAR_FILE=build\libs\fuckapi-0.0.1-SNAPSHOT.jar
if "%SPRING_DATASOURCE_URL%"=="" set SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/voting_db
if "%SPRING_DATASOURCE_USERNAME%"=="" set SPRING_DATASOURCE_USERNAME=postgres
if "%SPRING_DATASOURCE_PASSWORD%"=="" set SPRING_DATASOURCE_PASSWORD=postgres
if "%SERVER_PORT%"=="" set SERVER_PORT=8080

echo ==========================================
echo   Starting Voting API
echo ==========================================
echo.
echo Configuration:
echo   Database URL: %SPRING_DATASOURCE_URL%
echo   Database User: %SPRING_DATASOURCE_USERNAME%
echo   Server Port: %SERVER_PORT%
echo.
echo Press Ctrl+C to stop the application
echo ==========================================
echo.

REM Verificar que el JAR existe
if not exist "%JAR_FILE%" (
    echo ERROR: JAR file not found at %JAR_FILE%
    echo Please run build-jar.bat first
    pause
    exit /b 1
)

REM Ejecutar la aplicación
java -jar "%JAR_FILE%" ^
  --spring.datasource.url="%SPRING_DATASOURCE_URL%" ^
  --spring.datasource.username="%SPRING_DATASOURCE_USERNAME%" ^
  --spring.datasource.password="%SPRING_DATASOURCE_PASSWORD%" ^
  --server.port="%SERVER_PORT%"

pause
