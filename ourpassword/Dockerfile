# 1. OpenJDK 17 이미지 사용
FROM openjdk:17-jdk-slim

# 2. JAR 파일 복사
COPY ./build/libs/ourpassword-OUR_PASSWORD_0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]