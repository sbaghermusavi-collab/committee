FROM devops01.icico.net.ir/amazoncorretto:17-opentelemetry

COPY target/*.jar /app.jar


EXPOSE 8080

ENV PROFILE=prd JVM_ARGS=""

ENV TZ=Asia/Tehran

VOLUME /var/log/nicico /var/nicico

CMD java $JVM_ARGS -Djava.security.egd=file:/dev/urandom -Dspring.profiles.active=$PROFILE -Dspring.redis.host=redis -jar /app.jar
