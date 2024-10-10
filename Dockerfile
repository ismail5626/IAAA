FROM openjdk:latest
COPY ./target/IAAACoursework-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "IAAACoursework-0.1.0.2-jar-with-dependencies.jar"]