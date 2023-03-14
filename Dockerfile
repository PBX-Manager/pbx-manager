FROM eclipse-temurin:17-jre-jammy

WORKDIR /app
COPY build/libs/pbxmanager-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_PROFILES_ACTIVE=docker

EXPOSE 8080

CMD java -jar app.jar
