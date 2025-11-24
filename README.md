# Sistema de Votación API

API REST para sistema de votación electoral desarrollada con Spring Boot y PostgreSQL.

## Inicio Rápido

### 1. Generar el JAR ejecutable

**Linux/Mac:**
```bash
./build-jar.sh
```

**Windows:**
```cmd
build-jar.bat
```

### 2. Configurar PostgreSQL

```sql
CREATE DATABASE voting_db;
```

### 3. Ejecutar la aplicación

**Linux/Mac:**
```bash
./run-jar.sh
```

**Windows:**
```cmd
run-jar.bat
```

La API estará disponible en: `http://localhost:8080`

## Configuración

### Variables de Entorno

Puedes configurar la aplicación usando variables de entorno:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/voting_db
export SPRING_DATASOURCE_USERNAME=postgres
export SPRING_DATASOURCE_PASSWORD=tu_password
export SERVER_PORT=8080
```

### Ejecución Manual

```bash
java -jar build/libs/fuckapi-0.0.1-SNAPSHOT.jar \
  --spring.datasource.url=jdbc:postgresql://localhost:5432/voting_db \
  --spring.datasource.username=postgres \
  --spring.datasource.password=tu_password
```

## Documentación

- **API Documentation**: Ver [API_DOCUMENTATION.md](API_DOCUMENTATION.md)
- **Deployment Guide**: Ver [DEPLOYMENT.md](DEPLOYMENT.md)

## Requisitos

- Java 21 o superior
- PostgreSQL 12 o superior
- Acceso a internet (para Migo API)

## Endpoints Principales

- `POST /api/persons/register` - Registrar persona
- `POST /api/candidates` - Crear candidato
- `POST /api/candidates/vote` - Registrar voto
- `GET /api/candidates` - Listar candidatos
- `GET /api/statistics` - Estadísticas generales
- `POST /api/comments` - Crear comentario
- `POST /api/admin/verify` - Verificar admin

## Estructura del Proyecto

```
fuckapi/
├── src/
│   └── main/
│       ├── java/com/yart/fuckapi/
│       │   ├── controller/     # Controladores REST
│       │   ├── service/        # Lógica de negocio
│       │   ├── repository/     # Acceso a datos
│       │   ├── model/          # Entidades JPA
│       │   ├── dto/            # Objetos de transferencia
│       │   └── config/         # Configuración
│       └── resources/
│           └── application.properties
├── build.gradle                # Configuración Gradle
├── API_DOCUMENTATION.md        # Documentación de la API
├── DEPLOYMENT.md               # Guía de despliegue
├── build-jar.sh/.bat          # Scripts para generar JAR
└── run-jar.sh/.bat            # Scripts para ejecutar JAR
```

## Tecnologías

- **Spring Boot 4.0.0** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **PostgreSQL** - Base de datos
- **Lombok** - Reducción de código boilerplate
- **Gradle** - Gestión de dependencias

## Integración Externa

- **Migo API** - Validación de DNI y obtención de nombres

## Licencia

Este proyecto es de uso educativo.
