# Use the official Gradle image with JDK 11 and Gradle 6.8.3
FROM gradle:6.8.3-jdk11 as builder

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY gradlew /app/gradlew
COPY gradle /app/gradle

# Copy all the project files
COPY . /app

# Make the Gradle wrapper script executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build

# Use the official OpenJDK 11 image for running the application
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built application from the builder stage
COPY --from=builder /app/build/libs/*.jar /app/app.jar

# Expose the port the app runs on (optional, change if necessary)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]