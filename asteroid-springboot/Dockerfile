FROM gradle:jdk17-alpine AS build
WORKDIR /src
COPY . /src
RUN gradle build

FROM openjdk:17-jdk-alpine
COPY --from=build /src/build/libs/asteroid-0.0.1-SNAPSHOT.jar asteroid.jar
EXPOSE 8080
RUN chmod +x asteroid.jar
ENTRYPOINT ["java", "-jar", "asteroid.jar"]
