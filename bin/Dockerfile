FROM openjdk:17
WORKDIR /app
COPY ./AdFraudDetection-0.0.1-SNAPSHOT.jar /app.jar
RUN mkdir /app/logs
COPY ./logback-spring.xml /app/
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
