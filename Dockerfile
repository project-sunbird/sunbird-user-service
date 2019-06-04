FROM openjdk:8-jre-alpine
RUN apk update \
    && apk add  unzip \
    && apk add curl \
    && adduser -u 1001 -h /home/sunbird/ -D sunbird \
    && mkdir -p /home/sunbird/learner
RUN chown -R sunbird:sunbird /home/sunbird
USER sunbird
COPY ./service/target/user-service-1.0-SNAPSHOT-dist.zip /home/sunbird/learner/
RUN unzip /home/sunbird/learner/user-service-1.0-SNAPSHOT-dist.zip -d /home/sunbird/learner/
WORKDIR /home/sunbird/learner/
CMD java  -cp '/home/sunbird/learner/user-service-1.0-SNAPSHOT/lib/*' play.core.server.ProdServerStart  /home/sunbird/learner/user-service-1.0-SNAPSHOT
