FROM maven:3.9.2-eclipse-temurin-8-alpine

COPY out/artifacts/starter_project_jar/starter-project.jar .

RUN java -version

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "./starter-project.jar"]
