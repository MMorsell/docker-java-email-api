#using alpine distro
FROM openjdk:12-alpine
COPY EmailApi.jar /usr/src/
WORKDIR /usr/src/

#cheating a little here since I have a .jar file, real way is to -javac then -java
ENTRYPOINT java -jar EmailApi.jar
#RUN javac EmailApi.java
#CMD ["java", "EmailApi.jar"]
