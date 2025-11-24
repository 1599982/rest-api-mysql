@echo off
echo ==========================================
echo   Building Voting API JAR
echo ==========================================
echo.

REM Limpiar builds anteriores
echo Cleaning previous builds...
call gradlew.bat clean

REM Compilar y generar JAR
echo.
echo Building JAR file...
call gradlew.bat build -x test

REM Verificar si el build fue exitoso
if %ERRORLEVEL% EQU 0 (
    echo.
    echo ==========================================
    echo   Build Successful!
    echo ==========================================
    echo.
    echo JAR file location:
    echo   build\libs\fuckapi-0.0.1-SNAPSHOT.jar
    echo.
    echo To run the application:
    echo   java -jar build\libs\fuckapi-0.0.1-SNAPSHOT.jar
    echo.
    echo For more deployment options, see DEPLOYMENT.md
    echo.
) else (
    echo.
    echo ==========================================
    echo   Build Failed!
    echo ==========================================
    echo.
    echo Please check the error messages above.
    exit /b 1
)

pause
