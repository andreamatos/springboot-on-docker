FROM openjdk:11
ARG JAR_FILE=target/starbucks-api-1.0.0-exec.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8089
CMD ["./app.jar"]