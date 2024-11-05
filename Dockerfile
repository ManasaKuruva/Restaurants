# Use a Maven image to build the application
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
# Copy the entire project to the container
COPY . .
# Build the project and skip tests to create the JAR file
RUN mvn clean package -DskipTests

# Use a smaller image to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copy the JAR file from the build stage
COPY --from=build /app/target/restaurants-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
