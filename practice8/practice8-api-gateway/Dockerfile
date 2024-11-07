FROM gradle:8.7-jdk21-alpine as build

ADD . /project
WORKDIR /project

# Черномуров Семён Андреевич ИКБО-10-21
ARG JAR_FILE=practice8-api-gateway.jar
RUN gradle build -x test
RUN ["sh", "-c", "jar -xf build/libs/${JAR_FILE}"]
RUN jdeps --ignore-missing-deps -q \
    --recursive \
    --multi-release 21 \
    --print-module-deps \
    --class-path 'BOOT-INF/lib/*' \
    build/libs/${JAR_FILE} > deps.info

RUN jlink \
    --add-modules $(cat deps.info) \
    --strip-debug \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output /myjre

FROM alpine:latest

ARG JAR_FILE=practice8-api-gateway.jar
RUN mkdir /java
ENV JAVA_HOME /java
ENV PATH $JAVA_HOME/bin:$PATH

COPY --from=build /myjre $JAVA_HOME
COPY --from=build /project/build/libs/$JAR_FILE /libs/
COPY --from=build /project/build/resources /resources/

CMD ["java", "-Djavax.net.debug=ssl:handshake", "-jar", "libs/practice8-api-gateway.jar"]
