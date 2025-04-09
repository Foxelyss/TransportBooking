FROM openjdk:21-jdk
EXPOSE 8080

RUN groupadd spring && adduser spring -g spring
RUN mkdir /app

WORKDIR /app
RUN mkdir -p /app/databases && chown -R spring:spring /app/databases

USER spring:spring

RUN chmod +rw -R /app/databases
ARG JAR_FILE=build/libs/transportbooking-0.2.4.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]