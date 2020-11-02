FROM openjdk:11
ADD target/teste-stoom.jar teste-stoom.jar
EXPOSE 8080:8080
ENTRYPOINT ["java", "-jar", "teste-stoom.jar"]