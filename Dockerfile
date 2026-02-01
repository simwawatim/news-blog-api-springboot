FROM eclipse-temurin:17-jdk-jammy


WORKDIR /app


COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

COPY src ./src

RUN ./gradlew clean bootJar --no-daemon

EXPOSE 8080

ENTRYPOINT ["java","-jar","build/libs/blog-0.0.1-SNAPSHOT.jar"]
