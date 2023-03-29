FROM openjdk:17-alpine

RUN apk update && apk add --no-cache tzdata

COPY target/*.jar app.jar
EXPOSE 8080/tcp

ENTRYPOINT ["java", "-jar", "/app.jar"]