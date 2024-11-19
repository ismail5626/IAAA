FROM openjdk:latest
COPY ./target/iaaa.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "iaaa.jar", "db:3306", "10000"]