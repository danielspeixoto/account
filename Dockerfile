FROM openjdk:latest

ADD build/libs/account-1.0.jar app.jar
RUN sh -c 'touch /app.jar'

ARG JWT
ENV JWT ${JWT}
ARG MONGO_URI="mongodb://mongodb/account"
ENV MONGO_URI ${MONGO_URI}
ENTRYPOINT ["java", \
     "-Dspring.data.mongodb.uri=${MONGO_URI}", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-Djwt=${JWT}", \
    "-jar", \
    "/app.jar" \
    ]
EXPOSE 8080