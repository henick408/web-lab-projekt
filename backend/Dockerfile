# ---- Build Stage ----
FROM gradle:9.2.1-jdk21 AS builder
WORKDIR /home/gradle/project

# Kopiujemy pliki konfiguracyjne (cache dependencies)
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle
COPY gradlew ./

# Pobranie zależności (cache warstw)
RUN ./gradlew --no-daemon dependencies || true

# Kopiujemy cały projekt
COPY . .

# Budujemy JAR
RUN ./gradlew --no-daemon bootJar -x test

# ---- Run Stage ----
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Kopiujemy gotowy plik JAR
COPY --from=builder /home/gradle/project/build/libs/*.jar ./app.jar

# Uruchamiamy jako nie-root
RUN adduser -D appuser
USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
