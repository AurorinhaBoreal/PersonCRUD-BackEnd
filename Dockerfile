FROM ubuntu:24.04 AS build
# Update the system, install open-jdk and clear package caches
RUN apt-get update && apt-get install openjdk-17-jdk -y && rm -rf /var/lib/apt/lists/*
COPY . .
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /build/libs/demo-1.jar app.jar

ENTRYPOINT [ "java", "jar", "app.jar" ]