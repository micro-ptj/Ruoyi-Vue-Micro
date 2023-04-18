FROM nginx:1.22.0

MAINTAINER PTJ

COPY dist /web/

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

EXPOSE  80
