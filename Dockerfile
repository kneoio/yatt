FROM openjdk:11
MAINTAINER kaira
EXPOSE 8080 8081
COPY maven /maven/
CMD java -jar ./maven/yatt-0.0.1-SNAPSHOT.jar
