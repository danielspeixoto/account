FROM openjdk:latest

ADD build/libs/account-1.0.jar app.jar
RUN sh -c 'touch /app.jar'

ENV JWT 0
ENV MONGO_URI "accountdb://mongodb/account"
ENV BROKER_USERNAME guest
ENV BROKER_PASSWORD guest
CMD java \
    -Dspring.data.mongodb.uri=$MONGO_URI \
    -Djava.security.egd=file:/dev/./urandom \
    -DJWT=$JWT \
    -DBROKER_USERNAME=$BROKER_USERNAME \
    -DBROKER_PASSWORD=$BROKER_PASSWORD \
    -jar \
    app.jar
EXPOSE 8080