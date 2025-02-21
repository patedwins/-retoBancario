FROM openjdk:11-jre-slim

WORKDIR /app

ARG USER_ARG
ARG PASSWORD_ARG
ARG URL_ARG

ENV USER_ENV=$USER_ARG
ENV PASSWORD_ENV=$PASSWORD_ARG
ENV URL_ENV=$URL_ARG

COPY /reto-service/build/libs/reto-service-1.0.0-SNAPSHOT.jar reto-1.0.0.jar

CMD ["java", "-Xmx200m", "-jar", "reto-1.0.0.jar"]

EXPOSE 8087
