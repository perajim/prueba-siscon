# API Employees

Este proyecto es una **API RESTful** desarrollada en Java 17 con Spring Boot 3.x y Maven que permite gestionar empleados mediante operaciones CRUD. La solución sigue principios de POO, principios **S.O.L.I.D.** Y **arquitectura hexagonal**

---

## Características

- Desarrollo con Spring Boot.
- Gestión de dependencias mediante Maven.
- Estructura de proyecto estándar para aplicaciones Java.

---

## Tecnologías utilizadas

- Java 17
- Spring Boot 3.x
- Maven
- H2 
- Swagger/OpenAPI
- JUnit 5 + Mockito
- Lombok
- YAML 

---

## Estructura del proyecto

```
prueba-siscon/
├── .mvn/                 # Archivos de configuración de Maven Wrapper
├── src/
│   ├── main/
│   │   ├── java/         # Código fuente principal
│   │   └── resources/    # Recursos como application.properties
├── .gitignore            # Archivos y carpetas ignorados por Git
├── mvnw                  # Script para ejecutar Maven Wrapper en Unix
├── mvnw.cmd              # Script para ejecutar Maven Wrapper en Windows
├── pom.xml               # Archivo de configuración de Maven
```

---

## Requisitos previos

- Java 17 o superior instalado.
- Maven instalado o utilizar el Maven Wrapper incluido.

---

## Instalación y ejecución

1. Clona el repositorio:
   ```bash
   git clone https://github.com/perajim/prueba-siscon.git
   cd prueba-siscon
   ```

2. Compila el proyecto:
   ```bash
   ./mvnw clean install
   ```

3. Ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```

   ---


## Modelo de Datos: `Empleado`

| Campo             | Tipo         | Descripción                      |
|-------------------|--------------|----------------------------------|
| firstName         | String       | Primer nombre del empleado       |
| middleName        | String       | Apellido materno                 |
| lastName          | String       | Apellido paterno                 |
| age               | Integer      | Edad                             |
| gender            | String       | Sexo (M/F)                       |
| birthDate         | String       | Formato `dd-MM-yyyy`             |
| position          | String       | Puesto del empleado              |

---

## Endpoints
| Método	   | Endpoint	          | Descripción                    |
|-----------|---------------------|--------------------------------|
| GET       | /api/employees      | Lista todos los empleados      |
| GET       | /api/employee/{id}  | Lista todos los empleados      |
| POST      | /api/employee       | Inserta un empleado |
| POST      | /api/employees      | Inserta varios empleados |
| PUT       | /api/employee/{id}  | Actualiza un empleado por ID   |
| DELETE    | /api/employee/{id}  | Elimina un empleado por ID     |

---

## Pruebas
Ejecutar pruebas unitarias:
   ```bash
   ./mvnw test
   ```

---

## Documentación Swagger
Una vez iniciado el proyecto, accede a:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Documentacion
- Colección de **Postman** incluida (`challenge-siscon-employee.postman_collection.json`)  
- Documentación de contrato OpenAPI (`api-docs.yaml`)