FROM openjdk:8-jre-alpine
RUN apk update \
    && apk add  unzip \
    && apk add curl \
    && adduser -u 1001 -h /home/sunbird/ -D sunbird \
    && mkdir -p /home/sunbird/
RUN chown -R sunbird:sunbird /home/sunbird
USER sunbird
COPY ./user-service/target/user-service-1.0.0-SNAPSHOT-dist.zip /home/sunbird/
RUN unzip /home/sunbird/user-service-1.0.0-SNAPSHOT-dist.zip -d /home/sunbird/
WORKDIR /home/sunbird/
CMD java  -cp '/home/sunbird/user-service-1.0.0-SNAPSHOT/lib/*' play.core.server.ProdServerStart  /home/sunbird/user-service-1.0.0-SNAPSHOT
