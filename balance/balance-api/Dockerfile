FROM azul/zulu-openjdk-alpine:17-latest

RUN apk update && apk add --no-cache tzdata

COPY target/*.jar app.jar
EXPOSE 8080/tcp

ENTRYPOINT ["java", "-jar", "/app.jar"]