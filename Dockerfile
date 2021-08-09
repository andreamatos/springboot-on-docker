FROM openjdk:11
COPY starbucks-api-1.0.0.jar /projetos/pessoal/starbucks-api/
ENTRYPOINT ["java","-jar","starbucks-api-1.0.0.jar"]