FROM maven:3.9-amazoncorretto-21 AS builder
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn -e -B package -DskipTests

FROM openjdk:21
COPY --from=builder /target/hotel-services-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8081
CMD ["java", "-jar", "/app.jar"]