# Bootcamp Backend - Prueba Técnica NTT DATA

Proyecto realizado usando Spring Boot como parte de una prueba técnica para NTT DATA.

## Descripción

El proyecto expone un endpoint para obtener usuarios aleatorios desde una API externa y almacenarlos en base de datos.

Endpoint:

```http id="n9x34l"
GET /users/random?count=10
```

El parámetro `count` permite indicar cuántos usuarios se desean obtener.

---

## Arquitectura

El proyecto fue desarrollado usando arquitectura por capas para mantener el código más ordenado y separar responsabilidades.

Las capas utilizadas fueron:

* Controller
* Service
* Repository
* DTO
* Entity
* Mapper

La idea fue mantener una estructura simple pero organizada para que el proyecto sea más fácil de mantener y escalar.

---

## Funcionamiento

Cuando se consume el endpoint:

```http id="4hkj7m"
GET /users/random
```

el sistema realiza lo siguiente:

1. Consume una API externa de usuarios aleatorios.
2. Convierte la información recibida a entidades del proyecto.
3. Valida si el usuario ya existe mediante el email.
4. Guarda únicamente los nuevos usuarios.
5. Retorna la información procesada.

---

## Validación de usuarios repetidos

El servicio fue pensado para no sobrescribir información existente.

Antes de guardar un usuario, se valida si ya existe previamente usando el email:

```java id="g5zk0a"
userRepository.findByEmail(user.getEmail())
```

Si el usuario ya existe:

* no se vuelve a guardar,
* no se sobrescribe la información,
* y se reutiliza la data ya existente en base de datos.

Si el usuario no existe:

* se almacena normalmente.

La idea de esto fue evitar duplicados y mantener la integridad de la información de manera simple.

---

## Ejecución del proyecto

Para ejecutar el proyecto en Windows se utilizó el siguiente comando:

```bash id="g7m1fx"
.\mvnw.cmd spring-boot:run
```

---

## Tecnologías usadas

* Java
* Spring Boot
* Spring Data JPA
* Lombok
* Maven


---

## Puntos de mejora

Como posibles mejoras para una versión más escalable del proyecto, se podría implementar:

* **MapStruct**

  Para manejar los mapeos entre DTOs y entidades de una manera más limpia y ordenada, evitando código repetitivo y facilitando el mantenimiento.

* **Migraciones de base de datos**

  Usando herramientas como Flyway o Liquibase para controlar cambios en la base de datos de manera versionada, especialmente pensando en un proyecto más grande o escalable.

La idea en esta prueba fue mantener una solución simple y funcional, pero dejando abierta la posibilidad de mejorar la estructura a futuro.
.

## Autor

Ronald Gustavo Siancas Garay
