# Disfraces Rivera

Aplicación web para la gestión y reserva de disfraces.
Permite a los usuarios registrarse, iniciar sesión, explorar un catálogo, filtrar, buscar y realizar reservas con validación de fechas.

---

## Funcionalidades

### Usuarios

* Registro con validaciones:

  * Nombre sin números
  * Correo válido (`@` y dominio)
* Inicio de sesión
* Persistencia de sesión (LocalStorage)

---

### Catálogo

* Visualización de disfraces
* Carga de **6 disfraces aleatorios**
* Filtro por categoría
* Búsqueda por texto
* Sección de **disfraces relacionados**

---

### Reservas

* Selección de fechas (inicio y fin)
* Validación de disponibilidad
* Liberación automática cuando la fecha termina
* Registro en base de datos

---

### Administración

* Rol `ADMIN`
* Puede:

  * Crear disfraces
  * Subir imágenes
  * Gestionar datos

---

### Notificaciones

* Envío de correo al administrador cuando se realiza una reserva

---

## Tecnologías utilizadas

### Backend

* Java 21+
* Spring Boot
* Spring Data JPA
* MySQL
* Lombok

### Frontend

* HTML
* CSS (Bootstrap)
* JavaScript

### Otros

* Postman (pruebas)
* Git & GitHub

---

## Configuración del proyecto

### 1. Clonar repositorio

```bash
git clone https://github.com/TU_USUARIO/disfraces-rivera.git
cd disfraces-rivera/backend
```

---

### 2. Configurar base de datos

Crear base de datos en MySQL:

```sql
CREATE DATABASE disfraces_rivera;
```

---

### 3. Configurar `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/disfraces_rivera
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

server.port=8080

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=TU_CORREO
spring.mail.password=TU_PASSWORD_APP
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

---

### 4. Ejecutar proyecto

```bash
mvn clean install
mvn spring-boot:run
```

---

## Endpoints principales

### Usuarios

* `POST /usuarios/registro`
* `POST /usuarios/login`

### Disfraces

* `GET /disfraces/aleatorios`
* `GET /disfraces/buscar?texto=`

### Reservas

* `POST /reservas/crear`

---

## Lógica importante

* Validación de fechas para evitar reservas inválidas
* Actualización automática del estado de reservas
* Control de stock de disfraces
* Relación entre usuarios, disfraces y reservas

---

## Estructura del proyecto

```
backend/
 ├── controller/
 ├── service/
 ├── repository/
 ├── model/
 └── config/

frontend/
 ├── html/
 ├── js/
 └── css/
```

---

## Estado del proyecto

✅ Backend funcional
✅ Conexión a base de datos
✅ Catálogo dinámico
✅ Sistema de reservas
✅ Envío de correos

---

## Autor

* Juan David Puentes Valencia

---

## Notas

Este proyecto fue desarrollado como práctica de ingeniería de software, aplicando conceptos de:

* CRUD
* Arquitectura en capas
* Validaciones
* Integración backend/frontend
