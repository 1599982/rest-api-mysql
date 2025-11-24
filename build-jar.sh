#!/bin/bash

echo "=========================================="
echo "  Building Voting API JAR"
echo "=========================================="
echo ""

# Limpiar builds anteriores
echo "Cleaning previous builds..."
./gradlew clean

# Compilar y generar JAR
echo ""
echo "Building JAR file..."
./gradlew build -x test

# Verificar si el build fue exitoso
if [ $? -eq 0 ]; then
    echo ""
    echo "=========================================="
    echo "  Build Successful!"
    echo "=========================================="
    echo ""
    echo "JAR file location:"
    echo "  build/libs/fuckapi-0.0.1-SNAPSHOT.jar"
    echo ""
    echo "To run the application:"
    echo "  java -jar build/libs/fuckapi-0.0.1-SNAPSHOT.jar"
    echo ""
    echo "For more deployment options, see DEPLOYMENT.md"
    echo ""
else
    echo ""
    echo "=========================================="
    echo "  Build Failed!"
    echo "=========================================="
    echo ""
    echo "Please check the error messages above."
    exit 1
fi
