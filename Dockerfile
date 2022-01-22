FROM eclipse-temurin:16
EXPOSE 8080
ADD demo.jar app.jar
ENTRYPOINT  ["java", "-jar", "/app.jar"]