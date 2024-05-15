FROM openjdk:18-jdk-alpine3.14
COPY build/libs/*.jar app.jar
COPY /src/main/resources/application.yml application.yml
COPY /src/main/resources/application-dev.yml application-dev.yml
ENTRYPOINT ["java","-jar","/app.jar", "--spring.config.location=file:application.yml"]