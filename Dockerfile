FROM openjdk:21

WORKDIR /app

COPY api5-0.0.1-SNAPSHOT.jar /app/api5.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/api5.jar"]
