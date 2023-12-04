# Etapa 1: Build da aplicação usando Maven
FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
 
# Etapa 2: Configuração do ambiente de execução
FROM openjdk:11-jdk-slim
WORKDIR /app
COPY --from=build /app/target/curriculo-0.0.1-SNAPSHOT.jar /app/curriculo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/curriculo.jar"]
