# DWF-01L-Desafío 2

Desafío práctico DWF 2 del grupo de laboratorio 01L, desarrollado en parejas.  
Este proyecto es una **API REST** construida con **Spring Boot** para la gestión de libros.  
Permite registrar, listar (con paginación y filtrado), actualizar y eliminar libros.

---

## 🛠 Tecnologías utilizadas
- Spring Boot (Web, Data JPA, Validation)  
- Base de datos H2 (en memoria)  
- Lombok  
- Springdoc OpenAPI (Swagger UI)  

---

## 📂 Estructura del proyecto
bookapi/
├── src/
│ ├── main/java/sv/edu/udb/bookapi/
│ │ ├── controller/ → Controladores REST
│ │ ├── dto/ → DTOs de entrada/salida con validaciones
│ │ ├── exception/ → Manejador global de excepciones
│ │ ├── model/ → Entidad JPA
│ │ ├── repository/ → Repositorio JPA
│ │ └── service/ → Lógica de negocio
│ └── resources/
│ ├── application.properties
│ └── static/
├── pom.xml
└── README.md



---

## Cómo ejecutar el proyecto

### Método 1: Usando Maven
```bash
./mvnw spring-boot:run

Método 2: Desde IntelliJ IDEA

Abrir el proyecto en IntelliJ

Ejecutar BookapiApplication.java

📊 Base de datos (H2 en memoria)

URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

Usuario: sa

Contraseña: (vacía)

Ejemplos de peticiones

Crear un libro
POST http://localhost:8080/api/books
Content-Type: application/json

{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "publicationYear": 2008,
  "description": "Un libro esencial para desarrolladores"
}

Listar libros
GET http://localhost:8080/api/books?page=0&size=5&title=clean

Actualizar un libro

PUT /api/books/1
Content-Type: application/json

{
  "title": "Clean Code (Edición actualizada)",
  "author": "Robert C. Martin",
  "publicationYear": 2009,
  "description": "Descripción actualizada"
}

Eliminar un libro

DELETE http://localhost:8080/api/books/1
