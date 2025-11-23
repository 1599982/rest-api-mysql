# API Documentation - Sistema de Votación

Base URL: `http://localhost:8080`

## Tabla de Contenidos
- [Person Endpoints](#person-endpoints)
- [Candidate Endpoints](#candidate-endpoints)
- [Comment Endpoints](#comment-endpoints)
- [Vote Endpoint](#vote-endpoint)

---

## Person Endpoints

### Registrar Persona

Registra una nueva persona en el sistema o retorna la existente si ya está registrada.

**Endpoint:** `POST /api/persons/register`

**Request Body:**
```json
{
  "dni": "43451826"
}
```

**Response (201 Created):**
```json
{
  "dni": "43451826",
  "nombre": "CASTILLO GOMES VANESSA SILVIA",
  "newRegistration": true,
  "hasVotedPresident": false,
  "hasVotedMayor": false,
  "message": "Person registered successfully"
}
```

**Response (persona ya existente que ha votado):**
```json
{
  "dni": "43451826",
  "nombre": "CASTILLO GOMES VANESSA SILVIA",
  "newRegistration": false,
  "hasVotedPresident": true,
  "hasVotedMayor": false,
  "message": "Person already registered"
}
```

---

## Candidate Endpoints

### Crear Candidato

Crea un nuevo candidato en el sistema. Si el DNI no existe en la tabla Person, se crea automáticamente.

**Endpoint:** `POST /api/candidates`

**Request Body:**
```json
{
  "dni": "87654321",
  "politicalParty": "Partido Democrático",
  "description": "Candidato con experiencia en gestión pública",
  "imageUri": "https://example.com/image.jpg",
  "roleType": "PRESIDENT"
}
```

**Valores válidos para `roleType`:**
- `PRESIDENT` - Presidente
- `MAYOR` - Alcalde

**Response (201 Created):**
```json
{
  "dni": "87654321",
  "nombre": "PEREZ GARCIA JUAN CARLOS",
  "politicalParty": "Partido Democrático",
  "description": "Candidato con experiencia en gestión pública",
  "imageUri": "https://example.com/image.jpg",
  "roleType": "PRESIDENT",
  "votes": 0,
  "message": "Candidate created successfully"
}
```

**Response Error (400 Bad Request):**
```json
{
  "error": "Candidate with DNI 87654321 already exists"
}
```

---

### Actualizar Candidato

Actualiza la información de un candidato existente (excepto el DNI y los votos).

**Endpoint:** `PUT /api/candidates/{dni}`

**Request Body (todos los campos son opcionales):**
```json
{
  "politicalParty": "Nuevo Partido",
  "description": "Nueva descripción actualizada",
  "imageUri": "https://example.com/new-image.jpg",
  "roleType": "MAYOR"
}
```

**Response (200 OK):**
```json
{
  "dni": "87654321",
  "nombre": "PEREZ GARCIA JUAN CARLOS",
  "politicalParty": "Nuevo Partido",
  "description": "Nueva descripción actualizada",
  "imageUri": "https://example.com/new-image.jpg",
  "roleType": "MAYOR",
  "votes": 0,
  "message": "Candidate updated successfully"
}
```

**Response Error (404 Not Found):**
```json
{
  "error": "Candidate with DNI 87654321 not found"
}
```

---

### Obtener Todos los Candidatos

Retorna la lista completa de candidatos registrados.

**Endpoint:** `GET /api/candidates`

**Response (200 OK):**
```json
[
  {
    "dni": "87654321",
    "nombre": "PEREZ GARCIA JUAN CARLOS",
    "politicalParty": "Partido Democrático",
    "description": "Candidato con experiencia",
    "imageUri": "https://example.com/image1.jpg",
    "roleType": "PRESIDENT",
    "votes": 5,
    "message": null
  },
  {
    "dni": "12345678",
    "nombre": "LOPEZ MARTINEZ MARIA ELENA",
    "politicalParty": "Partido Popular",
    "description": "Candidata comprometida",
    "imageUri": "https://example.com/image2.jpg",
    "roleType": "MAYOR",
    "votes": 3,
    "message": null
  }
]
```

---

### Obtener Candidatos por Rol

Retorna los candidatos filtrados por tipo de cargo (PRESIDENT o MAYOR).

**Endpoint:** `GET /api/candidates/role/{roleType}`

**Ejemplo:** `GET /api/candidates/role/PRESIDENT`

**Response (200 OK):**
```json
[
  {
    "dni": "87654321",
    "nombre": "PEREZ GARCIA JUAN CARLOS",
    "politicalParty": "Partido Democrático",
    "description": "Candidato con experiencia",
    "imageUri": "https://example.com/image1.jpg",
    "roleType": "PRESIDENT",
    "votes": 5,
    "message": null
  }
]
```

---

### Obtener Candidato por DNI

Retorna la información de un candidato específico.

**Endpoint:** `GET /api/candidates/{dni}`

**Ejemplo:** `GET /api/candidates/87654321`

**Response (200 OK):**
```json
{
  "dni": "87654321",
  "nombre": "PEREZ GARCIA JUAN CARLOS",
  "politicalParty": "Partido Democrático",
  "description": "Candidato con experiencia",
  "imageUri": "https://example.com/image.jpg",
  "roleType": "PRESIDENT",
  "votes": 5,
  "message": null
}
```

**Response Error (404 Not Found):**
```json
{
  "error": "Candidate with DNI 87654321 not found"
}
```

---

## Vote Endpoint

### Registrar Voto

Registra el voto de una persona para un candidato. Una persona solo puede votar una vez por presidente y una vez por alcalde.

**Endpoint:** `POST /api/candidates/vote`

**Request Body:**
```json
{
  "voterDni": "43451826",
  "candidateDni": "87654321"
}
```

**Response (200 OK):**
```json
{
  "dni": "87654321",
  "nombre": "PEREZ GARCIA JUAN CARLOS",
  "politicalParty": "Partido Democrático",
  "description": "Candidato con experiencia",
  "imageUri": "https://example.com/image.jpg",
  "roleType": "PRESIDENT",
  "votes": 6,
  "message": "Vote registered successfully"
}
```

**Response Error (400 Bad Request):**

Si la persona no existe:
```json
{
  "error": "Person with DNI 43451826 not found"
}
```

Si el candidato no existe:
```json
{
  "error": "Candidate with DNI 87654321 not found"
}
```

Si ya votó por presidente:
```json
{
  "error": "Person has already voted for president"
}
```

Si ya votó por alcalde:
```json
{
  "error": "Person has already voted for mayor"
}
```

---

## Comment Endpoints

### Crear Comentario

Crea un nuevo comentario asociado a una persona. La persona debe estar registrada previamente.

**Endpoint:** `POST /api/comments`

**Request Body:**
```json
{
  "dni": "43451826",
  "description": "Este es mi comentario sobre el proceso electoral"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "dni": "43451826",
  "nombre": "CASTILLO GOMES VANESSA SILVIA",
  "datetime": "2025-11-23T10:30:45.123",
  "description": "Este es mi comentario sobre el proceso electoral",
  "message": "Comment created successfully"
}
```

**Response Error (400 Bad Request):**
```json
{
  "error": "Person with DNI 43451826 not found"
}
```

---

### Obtener Todos los Comentarios

Retorna la lista completa de comentarios registrados.

**Endpoint:** `GET /api/comments`

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "dni": "43451826",
    "nombre": "CASTILLO GOMES VANESSA SILVIA",
    "datetime": "2025-11-23T10:30:45.123",
    "description": "Este es mi comentario sobre el proceso electoral",
    "message": null
  },
  {
    "id": 2,
    "dni": "87654321",
    "nombre": "PEREZ GARCIA JUAN CARLOS",
    "datetime": "2025-11-23T11:15:30.456",
    "description": "Otro comentario importante",
    "message": null
  }
]
```

---

### Obtener Comentarios por DNI

Retorna todos los comentarios realizados por una persona específica.

**Endpoint:** `GET /api/comments/person/{dni}`

**Ejemplo:** `GET /api/comments/person/43451826`

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "dni": "43451826",
    "nombre": "CASTILLO GOMES VANESSA SILVIA",
    "datetime": "2025-11-23T10:30:45.123",
    "description": "Este es mi comentario sobre el proceso electoral",
    "message": null
  },
  {
    "id": 3,
    "dni": "43451826",
    "nombre": "CASTILLO GOMES VANESSA SILVIA",
    "datetime": "2025-11-23T12:00:00.789",
    "description": "Segundo comentario de la misma persona",
    "message": null
  }
]
```

---

## Notas Importantes

### Integración con Migo API

Todos los endpoints que retornan información de personas incluyen el campo `nombre`, el cual se obtiene automáticamente desde la API de Migo usando el DNI. Si la API de Migo no está disponible o falla, el campo `nombre` será `null`.

### CORS

Todos los endpoints tienen CORS habilitado con `origins = "*"`, permitiendo peticiones desde cualquier origen.

### Formato de Fechas

Las fechas se retornan en formato ISO 8601: `YYYY-MM-DDTHH:mm:ss.SSS`

### Códigos de Estado HTTP

- `200 OK` - Operación exitosa
- `201 Created` - Recurso creado exitosamente
- `400 Bad Request` - Error en la petición o validación
- `404 Not Found` - Recurso no encontrado

### Base de Datos

El sistema usa PostgreSQL y las tablas se crean automáticamente al iniciar la aplicación gracias a la configuración `spring.jpa.hibernate.ddl-auto=update`.
