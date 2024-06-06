FROM openjdk:20-jdk-slim-buster
WORKDIR /SpringFlowerShop
COPY ./target/SpringFlowerShop-0.0.1-SNAPSHOT.jar /SpringFlowerShop.jar
ENTRYPOINT ["java", "-jar", "/SpringFlowerShop.jar"]
