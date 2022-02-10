FROM adoptopenjdk:11-jre-hotspot
VOLUME /tmp
COPY ./build/libs/customerkata-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
