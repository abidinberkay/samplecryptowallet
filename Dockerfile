FROM maven:3.8.4-eclipse-temurin-17 AS build
COPY ./src /usr/app/src
COPY ./pom.xml /usr/app
RUN cd /usr/app && mvn clean package

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /usr/app/target/samplecryptowallet-0.0.1-SNAPSHOT.jar /usr/local/lib/samplecryptowallet.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/samplecryptowallet.jar"]


#FROM maven:3.8.4-eclipse-temurin-17 AS build
#COPY ./src /usr/app/src
#COPY ./pom.xml /usr/app
#RUN #cd /usr/app && mvn clean package
#
#FROM eclipse-temurin:17-jdk-alpine
#COPY --from=build ./target/samplecryptowallet-0.0.1-SNAPSHOT.jar /usr/local/lib/samplecryptowallet.jar
#EXPOSE 8080
##COPY --from=build /usr/app/target/*.jar samplecryptowallet.jar
#ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=DOCKER", "/usr/local/lib/samplecryptowallet.jar"]


#FROM t2yazilim/remiks-builder:latest
#COPY ./src /usr/app/src
#COPY ./pom.xml /usr/app
#RUN cd /usr/app && mvn clean package
##COPY --from=build ./target/*.jar demo-1.0.0.jar
#ENTRYPOINT ["java","-jar","/demo-1.0.0.jar"]


#FROM eclipse-temurin:17-jdk-alpine
#RUN mvn -f ~/pom.xml clean package
#COPY ./src /usr/app/src
#COPY ./pom.xml /usr/app
##COPY --from=build ./target/*.jar demo-1.0.0.jar
#ENTRYPOINT ["java","-jar","/demo-1.0.0.jar"]



#FROM maven:3.6.3-openjdk-17 AS build
#COPY src /usr/app/src
#COPY pom.xml /usr/app
#RUN mvn -f /usr/app/pom.xml clean package
#
#FROM openjdk
#EXPOSE 8080
#COPY --from=build /usr/app/target/*.jar samplecryptowallet.jar
#ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=DOCKER", "/samplecryptowallet-1.0.0.jar"]

#--platform linux/amd64,linux/arm64
#FROM openjdk AS build
#RUN mvn -f ./pom.xml clean package
#COPY ./src /usr/app/src
#COPY ./pom.xml /usr/app
##COPY --from=build ./target/*.jar samplecryptowallet-1.0.0.jar
#ENTRYPOINT ["java","-jar","/samplecryptowallet-1.0.0.jar"]