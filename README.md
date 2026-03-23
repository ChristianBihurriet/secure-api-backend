# 🔐 Secure API Backend (JWT Template)

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-brightgreen)
![Security](https://img.shields.io/badge/Security-JWT-orange)
![Build](https://img.shields.io/badge/build-passing-success)

Plantilla reutilizable de autenticación con JWT para proyectos backend en Spring Boot.
Incluye login, registro de usuarios, seguridad stateless y estructura lista para producción.

---

## 🧠 Proyecto de portfolio

Este proyecto está diseñado como una **base reutilizable** para acelerar el desarrollo de APIs seguras con Spring Boot, evitando tener que reconfigurar la seguridad en cada nuevo proyecto.

---

## ⚡ Quick Start

```bash
git clone https://github.com/ChristianBihurriet/secure-api-backend.git
cd secure-api-backend
mvn spring-boot:run
```

API disponible en:

```
http://localhost:8080
```

---

## 🚀 Características

* ✅ Autenticación con JWT
* ✅ Registro y login de usuarios
* ✅ Seguridad stateless (sin sesiones)
* ✅ Filtro JWT personalizado
* ✅ Manejo centralizado de errores
* ✅ Validación de datos
* ✅ Estructura reutilizable para nuevos proyectos

---

## 🏗️ Arquitectura

```text
Controller → Service → Repository → DB
             ↓
         Security Layer (JWT)
```

Incluye:

* **AuthController** → endpoints de login y registro
* **AuthService** → lógica de autenticación y generación de JWT
* **UserRepository** → acceso a usuarios
* **SecurityConfig** → configuración de seguridad

---

## 🔐 Seguridad (JWT)

El sistema utiliza:

* 🔑 Token JWT firmado con clave secreta
* 🛡️ Filtro que intercepta cada request
* 🚫 Acceso protegido por defecto
* ✅ Solo `/api/auth/**` es público

Flujo:

1. Usuario hace login
2. Se genera un JWT
3. Cliente envía el token en cada request
4. El filtro valida el token y autentica

---

## 📡 Endpoints

### 🔑 Autenticación

```http
POST /api/auth/login
POST /api/auth/signup
```

### 🧪 Test protegido

```http
GET /api/test
```

👉 Requiere JWT válido

---

## 📦 Ejemplo Login

```json
{
  "username": "admin",
  "password": "admin"
}
```

Respuesta:

```json
{
  "token": "jwt_token",
  "username": "admin",
  "type": "Bearer"
}
```

---

## 🔄 Uso del token

Enviar en headers:

```http
Authorization: Bearer <token>
```

El filtro JWT se encarga de validar automáticamente cada request

---

## 🧩 Modelo de usuario

* username único
* password encriptada (BCrypt)
* roles (USER / ADMIN)

---

## ⚙️ Configuración

### application.properties

```properties
app.jwt.secret=mi_clave_secreta_super_segura
app.jwt.expiration-ms=86400000

spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=update
```

---

## 🧪 Testing

Actualmente en desarrollo.
Se implementarán tests unitarios con JUnit y Mockito.

---

## 📈 Uso como plantilla

Este proyecto está pensado para:

* copiarlo como base
* integrar tu lógica de negocio
* reutilizar la configuración JWT

### Pasos típicos:

1. Clonar repositorio
2. Cambiar entidades
3. Reutilizar seguridad
4. Añadir nuevos endpoints

---

## 📸 Demo

*(Próximamente)*

---

## 📈 Roadmap

* 🔐 Roles y permisos avanzados
* 📄 Refresh tokens
* 🧪 Tests
* 📄 Swagger
* 🐳 Docker

---

## 👨‍💻 Autor

**Christian Bihurriet**
Backend Developer (Java + Spring Boot)

---

## ⭐ Notas

Plantilla pensada para evitar repetir configuraciones de seguridad en cada proyecto, siguiendo buenas prácticas de Spring Security y JWT.
