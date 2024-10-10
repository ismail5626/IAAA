FROM openjdk:latest
COPY ./target/IAAACoursework-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "IAAACoursework-1.0-SNAPSHOT-jar-with-dependencies.jar"]