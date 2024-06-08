# Base image
FROM openjdk:17

# Set the working directory
WORKDIR /app

# Copy the application JAR file
COPY build/libs/careerwry-posting-0.0.1-SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 9002

# Set environment variables
ENV SPRING_DATASOURCE_URL=jdbc:mysql://careerwry-mysql:3306/careerwry-posting
ENV SPRING_DATASOURCE_USERNAME=careerwry
ENV SPRING_DATASOURCE_PASSWORD=1q2w3e4r
ENV SPRING_PROFILES_ACTIVE=FILE
ENV SPRING_FLYWAY_BASELINE-ON-MIGRATE=true
ENV BPL_DEBUG_ENABLED=false
ENV BPL_DEBUG_PORT=8002
ENV BPL_JVM_THREAD_COUNT=100

# Mount the log directory
VOLUME /logs

# Command to run the application
CMD ["java", "-jar", "app.jar"]
