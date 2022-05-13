FROM openjdk:17-jdk-alpine3.14

COPY "./target/fruits.jar" "/application/fruits.jar"

CMD ["java", "-jar", "/application/fruits.jar"]