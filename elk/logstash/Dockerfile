FROM logstash

ADD . /

ENV BROKER_USER test
ENV BROKER_PASS test

CMD logstash -f /logstash.conf
EXPOSE 5000