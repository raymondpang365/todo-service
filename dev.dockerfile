# We’ll pick a Maven + JDK base image so we can build & test directly
FROM maven:3.9.5-eclipse-temurin-21

# Create a working directory
WORKDIR /app

# Copy the pom.xml first (so dependency resolution can be cached)
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copy source code. (We’ll override this with a volume in docker-compose, but copy anyway for initial build.)
COPY src ./src

# Optionally, run a build now (skipping tests so it doesn’t fail if we’re missing some pieces):
RUN mvn clean package -DskipTests

EXPOSE 8080

# By default, you might just start the app. But you can override this in docker-compose:
ENTRYPOINT ["mvn", "spring-boot:run"]