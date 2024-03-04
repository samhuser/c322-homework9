FROM eclipse-temurin:17
WORKDIR /home
COPY ./target/guitars-0.0.1.jar guitars.jar
ENTRYPOINT ["java", "-jar", "guitars.jar"]
LABEL authors="samhuser"