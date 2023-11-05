# This is a sample project for learning microservices using docker and many other tech

#Start with a base image containing Java runtime
FROM azul/zulu-openjdk:17

#Information around who maintains the image
MAINTAINER Vibhushit Singhal

# Add the application's jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "/accounts-0.0.1-SNAPSHOT.jar"]