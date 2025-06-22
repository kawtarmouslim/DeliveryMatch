FROM openjdk:17
ADD target/deliverymatch-0.0.1-SNAPSHOT.jar deliverymatch.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "deliverymatch.jar"]
