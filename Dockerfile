# ====== ETAPA 1: BUILD ======
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar archivo de configuración
COPY pom.xml .

# Descargar dependencias (cache)
RUN mvn -q dependency:go-offline || true

# Copiar el código fuente
COPY src ./src

# Compilar proyecto
RUN mvn -q clean package -DskipTests

# ====== ETAPA 2: RUNTIME ======
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiar JAR construido
COPY --from=build /app/target/*.jar app.jar

# Exponer puerto (ajusta si cambias el server.port)
EXPOSE 8080

# Ejecutar aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]