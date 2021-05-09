FROM openjdk:11-slim

ARG JAR_FILE=cake-manager2-1.0.0.jar
COPY target/$JAR_FILE /opt/cake-manager2-1.0.0.jar

RUN apt-get update
RUN apt-get -yq install curl
RUN apt-get -yq clean

EXPOSE 8281

CMD exec /usr/local/openjdk-11/bin/java $JAVA_OPTS -jar /opt/cake-manager2-1.0.0.jar
