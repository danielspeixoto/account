FROM openjdk:latest

ADD build/libs/account-1.0.jar app.jar
RUN sh -c 'touch /app.jar'

ENV JWT 0
ENV MONGO_URI "mongodb://mongodb/account"
ENV BROKER_USER test
ENV BROKER_PASS test
CMD java \
    -Dspring.data.mongodb.uri=$MONGO_URI \
    -Djava.security.egd=file:/dev/./urandom \
    -DJWT=$JWT \
    -DBROKER_USERNAME=$BROKER_USER \
    -DBROKER_PASSWORD=$BROKER_PASS \
    -jar \
    app.jar
EXPOSE 8080