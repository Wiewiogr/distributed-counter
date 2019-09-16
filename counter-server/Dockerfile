FROM openjdk:11.0.3-jre-slim

RUN apt-get update && apt-get upgrade -y && \
    apt-get install -qq -y curl

COPY target/*-shaded.jar application.jar

EXPOSE 8080

CMD ["sh", "-c" , "java ${PROPERTIES} -jar application.jar"]