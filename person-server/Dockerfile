FROM adoptopenjdk/openjdk8:alpine-jre

LABEL maintainer="rollin.hand@gmx.de"
LABEL version="${version}"

WORKDIR /opt

ADD target/person-server-meecrowave-distribution.tar.gz .

RUN apk --no-cache add bash

EXPOSE 8080
ENTRYPOINT ["sh", "/opt/person-server/bin/meecrowave.sh", "run"]