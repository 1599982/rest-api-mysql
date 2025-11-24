#!/bin/bash

# Configuración por defecto
JAR_FILE="build/libs/fuckapi-0.0.1-SNAPSHOT.jar"
DB_URL="${SPRING_DATASOURCE_URL:-jdbc:postgresql://localhost:5432/voting_db}"
DB_USER="${SPRING_DATASOURCE_USERNAME:-postgres}"
DB_PASS="${SPRING_DATASOURCE_PASSWORD:-postgres}"
SERVER_PORT="${SERVER_PORT:-8080}"

echo "=========================================="
echo "  Starting Voting API"
echo "=========================================="
echo ""
echo "Configuration:"
echo "  Database URL: $DB_URL"
echo "  Database User: $DB_USER"
echo "  Server Port: $SERVER_PORT"
echo ""
echo "Press Ctrl+C to stop the application"
echo "=========================================="
echo ""

# Verificar que el JAR existe
if [ ! -f "$JAR_FILE" ]; then
    echo "ERROR: JAR file not found at $JAR_FILE"
    echo "Please run ./build-jar.sh first"
    exit 1
fi

# Ejecutar la aplicación
java -jar "$JAR_FILE" \
  --spring.datasource.url="$DB_URL" \
  --spring.datasource.username="$DB_USER" \
  --spring.datasource.password="$DB_PASS" \
  --server.port="$SERVER_PORT"
