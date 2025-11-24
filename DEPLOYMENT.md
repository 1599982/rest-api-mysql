# Guía de Despliegue - Sistema de Votación

## Requisitos Previos

### En la máquina de desarrollo:
- Java JDK 21 o superior
- Gradle (incluido en el proyecto con Gradle Wrapper)

### En la máquina de producción:
- Java JRE 21 o superior
- PostgreSQL 12 o superior
- Acceso a internet (para Migo API)

---

## Generar el Archivo JAR Ejecutable

### Opción 1: Usando Gradle Wrapper (Linux/Mac)

```bash
./gradlew clean build
```

### Opción 2: Usando Gradle Wrapper (Windows)

```cmd
gradlew.bat clean build
```

### Opción 3: Usando Gradle instalado

```bash
gradle clean build
```

El archivo JAR se generará en: `build/libs/fuckapi-0.0.1-SNAPSHOT.jar`

---

## Configurar la Base de Datos PostgreSQL

### 1. Crear la base de datos

```sql
CREATE DATABASE voting_db;
```

### 2. Crear un usuario (opcional pero recomendado)

```sql
CREATE USER voting_user WITH PASSWORD 'tu_password_seguro';
GRANT ALL PRIVILEGES ON DATABASE voting_db TO voting_user;
```

---

## Configurar Variables de Entorno

Antes de ejecutar el JAR, configura las siguientes variables de entorno:

### Linux/Mac:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/voting_db
export SPRING_DATASOURCE_USERNAME=postgres
export SPRING_DATASOURCE_PASSWORD=tu_password
export SERVER_PORT=8080
```

### Windows (CMD):

```cmd
set SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/voting_db
set SPRING_DATASOURCE_USERNAME=postgres
set SPRING_DATASOURCE_PASSWORD=tu_password
set SERVER_PORT=8080
```

### Windows (PowerShell):

```powershell
$env:SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/voting_db"
$env:SPRING_DATASOURCE_USERNAME="postgres"
$env:SPRING_DATASOURCE_PASSWORD="tu_password"
$env:SERVER_PORT="8080"
```

---

## Ejecutar el JAR

### Ejecución básica:

```bash
java -jar build/libs/fuckapi-0.0.1-SNAPSHOT.jar
```

### Ejecución con configuración personalizada:

```bash
java -jar build/libs/fuckapi-0.0.1-SNAPSHOT.jar \
  --spring.datasource.url=jdbc:postgresql://localhost:5432/voting_db \
  --spring.datasource.username=postgres \
  --spring.datasource.password=tu_password \
  --server.port=8080
```

### Ejecución en segundo plano (Linux/Mac):

```bash
nohup java -jar build/libs/fuckapi-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
```

### Ejecución como servicio (Linux con systemd):

Crear archivo `/etc/systemd/system/voting-api.service`:

```ini
[Unit]
Description=Voting API Service
After=postgresql.service

[Service]
Type=simple
User=voting
WorkingDirectory=/opt/voting-api
ExecStart=/usr/bin/java -jar /opt/voting-api/fuckapi-0.0.1-SNAPSHOT.jar
Restart=on-failure
RestartSec=10

Environment="SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/voting_db"
Environment="SPRING_DATASOURCE_USERNAME=postgres"
Environment="SPRING_DATASOURCE_PASSWORD=tu_password"
Environment="SERVER_PORT=8080"

[Install]
WantedBy=multi-user.target
```

Comandos para gestionar el servicio:

```bash
sudo systemctl daemon-reload
sudo systemctl enable voting-api
sudo systemctl start voting-api
sudo systemctl status voting-api
```

---

## Verificar que la Aplicación Está Funcionando

### 1. Verificar el puerto:

```bash
curl http://localhost:8080/api/statistics
```

### 2. Verificar logs:

```bash
tail -f app.log
```

### 3. Verificar con systemd:

```bash
sudo journalctl -u voting-api -f
```

---

## Configuración de Producción

### Archivo application-prod.properties

Crear `application-prod.properties` junto al JAR:

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/voting_db
spring.datasource.username=postgres
spring.datasource.password=tu_password_seguro

# JPA
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false

# Logging
logging.level.root=INFO
logging.level.com.yart.fuckapi=INFO
logging.file.name=logs/application.log
```

Ejecutar con perfil de producción:

```bash
java -jar fuckapi-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

---

## Optimización de Memoria

### Configurar memoria JVM:

```bash
java -Xms512m -Xmx1024m -jar fuckapi-0.0.1-SNAPSHOT.jar
```

Donde:
- `-Xms512m`: Memoria inicial (512 MB)
- `-Xmx1024m`: Memoria máxima (1 GB)

---

## Solución de Problemas

### Error: "Could not connect to database"

1. Verificar que PostgreSQL está corriendo:
   ```bash
   sudo systemctl status postgresql
   ```

2. Verificar las credenciales de la base de datos

3. Verificar que el puerto 5432 está abierto

### Error: "Port 8080 already in use"

Cambiar el puerto:
```bash
java -jar fuckapi-0.0.1-SNAPSHOT.jar --server.port=8081
```

### Error: "OutOfMemoryError"

Aumentar la memoria JVM:
```bash
java -Xmx2048m -jar fuckapi-0.0.1-SNAPSHOT.jar
```

---

## Actualización de la Aplicación

1. Detener la aplicación actual:
   ```bash
   sudo systemctl stop voting-api
   ```

2. Hacer backup de la base de datos:
   ```bash
   pg_dump voting_db > backup_$(date +%Y%m%d).sql
   ```

3. Reemplazar el JAR antiguo con el nuevo

4. Iniciar la aplicación:
   ```bash
   sudo systemctl start voting-api
   ```

---

## Seguridad

### Recomendaciones:

1. **No exponer la aplicación directamente a internet**
   - Usar un proxy reverso (Nginx, Apache)
   - Configurar HTTPS/SSL

2. **Proteger las credenciales**
   - No incluir contraseñas en el código
   - Usar variables de entorno o archivos de configuración seguros

3. **Firewall**
   - Permitir solo los puertos necesarios
   - Restringir acceso a PostgreSQL

4. **Backups regulares**
   - Configurar backups automáticos de la base de datos
   - Probar la restauración periódicamente

---

## Monitoreo

### Ver logs en tiempo real:

```bash
tail -f logs/application.log
```

### Verificar uso de recursos:

```bash
top -p $(pgrep -f fuckapi)
```

### Verificar conexiones a la base de datos:

```sql
SELECT * FROM pg_stat_activity WHERE datname = 'voting_db';
```

---

## Contacto y Soporte

Para más información sobre la API, consultar `API_DOCUMENTATION.md`
