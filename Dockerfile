#===================================
#This is for Multi Stage Build
#===================================
#Stage 1: Build JAR
FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Stage 2: Create a minimal Docker image with the JAR
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar", "-Dspring-boot.run.profiles=mysql"]




#===================================
#//This is for Single Stage Build
#===================================

#FROM eclipse-temurin:17-jdk-jammy
#WORKDIR /app
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:resolve
#COPY src ./src
#CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=mysql"]
