FROM openjdk:latest

ADD build/libs/account-1.0.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java", \
     "-Dspring.data.mongodb.uri=mongodb://mongodb/account", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-Djwt=ADD_AS_ENV_VARIABLE", \
    "-jar", \
    "/app.jar" \
    ]
EXPOSE 8080