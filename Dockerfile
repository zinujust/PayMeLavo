# ========== STAGE 1: build ==========
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# deps layer (from last lesson)
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# code layer
COPY src ./src
RUN ./mvnw clean package -DskipTests
# → produces target/myapp-0.0.1-SNAPSHOT.jar

# ========== STAGE 2: runtime ==========
FROM eclipse-temurin:21-jre AS runtime
WORKDIR /app

# Create a system group + user named 'spring' (no login, no home dir needed)
RUN groupadd --system spring && useradd --system --gid spring spring

# Reach into the 'build' stage and grab ONLY the finished jar
COPY --from=build /app/target/*.jar app.jar

# Switch to that user — everything below runs as 'spring', not root
USER spring:spring

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]