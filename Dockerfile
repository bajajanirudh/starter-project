FROM openjdk:8

COPY target/starter-project-1.0-SNAPSHOT.jar .

EXPOSE 9000
ENV LD_BIND_NOW=1
ENTRYPOINT ["java", "-jar", "./starter-project-1.0-SNAPSHOT.jar"]
