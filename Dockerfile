FROM openjdk:21

WORKDIR /app

COPY build/libs/api5-0.0.1-SNAPSHOT.jar /app/api5.jar

EXPOSE 8080

# Команда для запуска приложения
CMD ["java", "-jar", "/app/api5.jar"]
