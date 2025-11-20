# ----------------------------------------------------------------------
# Giai đoạn 1: BUILD - Build ứng dụng bằng Maven + JDK 11
# ----------------------------------------------------------------------
FROM maven:3.9.5-jdk-11 AS build

WORKDIR /app

# Copy pom.xml trước để cache dependency
COPY pom.xml .

# Download toàn bộ dependencies trước để cache build nhanh hơn
RUN mvn dependency:go-offline -B

# Copy toàn bộ source
COPY src ./src

# Build ra JAR
RUN mvn clean package -DskipTests


# ----------------------------------------------------------------------
# Giai đoạn 2: RUNTIME - Image chạy thật (JRE nhẹ)
# ----------------------------------------------------------------------
FROM eclipse-temurin:11-jre-jammy

WORKDIR /app

# Copy file JAR đã build
COPY --from=build /app/target/*.jar app.jar

# Mở cổng server
EXPOSE 8080

# ENTRYPOINT tối ưu cho container
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=80", "-jar", "app.jar"]
