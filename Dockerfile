# ----------------------------------------------------------------------
# Giai đoạn 1: BUILD - Biên dịch và đóng gói ứng dụng (Sử dụng image phát triển)
# Base image: Maven với JDK 11
# ----------------------------------------------------------------------
FROM maven:3.9.5-eclipse-temurin-11-jammy AS build

# Đặt thư mục làm việc bên trong container
WORKDIR /app

# Sao chép các file cấu hình và mã nguồn
COPY pom.xml .
COPY src src

# Chạy lệnh Maven để đóng gói ứng dụng thành file .jar
# -DskipTests: Bỏ qua các bài kiểm tra để xây dựng nhanh hơn
RUN mvn clean package -DskipTests


# ----------------------------------------------------------------------
# Giai đoạn 2: RUNTIME - Tạo image sản phẩm cuối cùng (Sử dụng image chỉ có JRE nhẹ)
# Base image: JRE 11 nhẹ nhất
# ----------------------------------------------------------------------
FROM eclipse-temurin:11-jre-jammy

# Đặt thư mục làm việc
WORKDIR /app

# Sao chép file .jar đã build từ giai đoạn 'build'
COPY --from=build /app/target/*.jar app.jar

# Khai báo cổng mặc định của Spring Boot
EXPOSE 8080

# Lệnh mặc định để chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]