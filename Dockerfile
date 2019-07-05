FROM openjdk:8-jre-alpine
MAINTAINER "S M Y ALTAMASH" "smy.altamash@gmail.com"
RUN apk update \
    && apk add  unzip \
    && apk add curl \
    && adduser -u 1001 -h /home/sunbird/ -D sunbird \
    && mkdir -p /home/sunbird/ 
RUN chown -R sunbird:sunbird /home/sunbird
ADD . /home/sunbird/ 
RUN unzip /home/sunbird/user-org-service-1.0.0-dist.zip -d /home/sunbird/ 
USER sunbird
EXPOSE 9000
WORKDIR /home/sunbird/
CMD java  -cp '/home/sunbird/user-org-service-1.0.0/lib/*' play.core.server.ProdServerStart  /home/sunbird/user-org-service-1.0.0
