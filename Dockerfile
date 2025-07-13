FROM openjdk:21-jdk-slim
ARG JAR_FILE=build/libs/*.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

# 모든 것 삭제 (컨테이너 + 볼륨)
# docker-compose down -v
# docker-compose up -d