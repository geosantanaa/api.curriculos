# Use the official OpenJDK image as a base image
FROM ubunto:latest AS build

# Define the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container at /app/curriculo.jar
COPY target/curriculo-0.0.1-SNAPSHOT.jar /app/curriculo.jar

# Expose the port that the application will run on
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "curriculo.jar"]