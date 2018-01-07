FROM mongo

ENV ADMIN_USER super
ENV ADMIN_PASS super
ENV USER guest
ENV PASS guest
ENV DB account

COPY . /

VOLUME /opt/data

CMD ["/scripts/start.sh"]
EXPOSE 27017

