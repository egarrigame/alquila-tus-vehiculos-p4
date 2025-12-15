# Builder
FROM eclipse-temurin:25 as builder
WORKDIR /app

# instalar maven
RUN apt-get update && apt-get install -y maven

# Copiar pom y código
COPY pom.xml .
COPY src ./src

# Construir el JAR
RUN mvn clean package -DskipTests

# JAR resultado y JaVA jre
FROM eclipse-temurin:25-jre
WORKDIR /app

# Copiar el JAR
COPY --from=builder /app/target/*.jar app.jar

# Puerto Spring Boot
EXPOSE 8080

# Variables de entorno conigurables

ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/alquiler_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=backend
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]
