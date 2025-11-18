# Backend

- Este api está implementado con Java 21 y Spring Boot 3.3.1

## Instrucciones de Uso

## Comandos

- Para limpiar, instalar y ejecutar la aplicación sin pruebas:

  ```bash
  ./mvn clean install -DskipTests spring-boot:run
  ```

- Para levantar backend localmente directo desde el jar:

  ```bash
	cd C:\Test\backend\target
	java -jar mantenedor-0.0.1-SNAPSHOT.jar --server.port=8080
  ```

## Login Usuario

- Para realizar el dummy login, usa el siguiente JSON:

```json
{
  "email": "admin@example.com",
  "password": "kjkk&&h%mmEa54"
}
```

## Revisar la API

- Para acceder a la documentación de la API, visita:  
  [Swagger UI](http://localhost:8080/swagger-ui/index.html)

- Para acceder a la consola de H2, visita:  
  [H2 Console](http://localhost:8080/h2-console)  
  Configura la URL de JDBC como:  
  `jdbc:h2:mem:testdb`


## Levantar en Docker

- Para levantar en docker, separar en dos directorios las aplicaciones.
- Llamarlas backend y frontend
- Fuera de los dos directorios copiar el archivo docker-compose.yml afuera de los dos directorios, ejemplo: C:\test\backend y C:\test\frontend, copiar el compose en: C:\test\.
- Desde fuera de los directorios, realizar:

```bash
cd C:\Test> 
  ## Generar la imagen
  Subir el Docker Desktop como admin
  docker compose up -d --build
  docker compose logs -f app
  docker compose down
```