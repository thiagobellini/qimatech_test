FROM eclipse-temurin:17-jdk-alpine AS build
LABEL authors="Thiago Bellini"
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/qimatech_test-0.0.1-SNAPSHOT.jar qimatech_test.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "qimatech_test.jar"]