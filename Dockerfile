# Docker multi-stage build

# 1. Building the App with Maven
FROM maven:3-jdk-11

ADD . /challengeSrc
WORKDIR /challengeSrc

# Run Maven build
RUN mvn clean install

# Just using the build artifact and then removing the build-container
FROM adoptopenjdk/openjdk11:alpine-jre

VOLUME /tmp

# Add Spring Boot app.jar to Container
COPY --from=0 "/challengeSrc/target/challenge-0.0.1-SNAPSHOT.jar" /home/challenge/app.jar

RUN rm -f /challengeSrc

WORKDIR /home/challenge

EXPOSE 8080

RUN chmod 755 /home/challenge/app.jar
ENTRYPOINT [ "java", "-jar", "/home/challenge/app.jar" ]
