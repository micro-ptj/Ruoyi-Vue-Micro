FROM openjdk:11-jdk
LABEL maintainer=PTJ


#docker run -e PARAMS="--server.port 9090"
ENV PARAMS="--server.port=80 --spring.profiles.active=prod"
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

COPY *.jar /app.jar
EXPOSE 80

ENTRYPOINT ["/bin/sh","-c","java -Dfile.encoding=utf8 -Djava.security.egd=file:/dev/./urandom -jar app.jar ${PARAMS}"]