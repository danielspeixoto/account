FROM mongo

ENV ADMIN_USER guest
ENV ADMIN_PASS guest
ENV DB account

ADD . /

VOLUME /data/db

EXPOSE 27017
CMD ["/start.sh"]

