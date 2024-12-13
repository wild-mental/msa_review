# 1. Build stage
FROM eclipse-temurin:17-jdk as build_stage
WORKDIR /app
# Copy all files to the build stage
COPY . .
RUN ./gradlew bootJar --no-daemon

# 2. Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app
# Copy only the built JAR to the runtime stage
COPY --from=build_stage /app/build/libs/msa_review-aws.jar app.jar

# 3. Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
