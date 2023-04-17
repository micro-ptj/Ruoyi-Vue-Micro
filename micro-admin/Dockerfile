FROM hub.c.163.com/library/java:openjdk-8-jre
LABEL maintainer=PTJ

ENV PARAMS="--server.port=80 --spring.profiles.active=prod"
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

COPY *.jar /app.jar
EXPOSE 80

ENTRYPOINT ["/bin/sh","-c","java -Dfile.encoding=utf8 -Djava.security.egd=file:/dev/./urandom -Xms1G -Xmx1G -jar app.jar ${PARAMS}"]