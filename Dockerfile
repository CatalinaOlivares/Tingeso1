FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} tingeso1-0.0.1-SNAPSHOT.jar
COPY DATA.txt DATA.txt
EXPOSE 8080
ENTRYPOINT ["java","-jar","/tingeso1-0.0.1-SNAPSHOT.jar"]
