# use locally built jar; useful for quick testing
# DOES NOT REBUILD JAR!

FROM openjdk:17-jdk-alpine
COPY . /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/build/libs/asteroid-0.0.1-SNAPSHOT.jar"]
