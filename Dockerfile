# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target directory to the container
COPY target/vehicle-service-0.0.1-SNAPSHOT.jar /app/vehicle-service.jar

# Expose the service port
EXPOSE 9128

# Run the application
ENTRYPOINT ["java", "-jar", "/app/vehicle-service.jar"]

