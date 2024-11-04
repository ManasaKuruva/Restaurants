# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target directory of your project into the container
COPY target/restaurants-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on

EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
