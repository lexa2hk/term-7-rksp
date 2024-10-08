FROM gradle:8.7-jdk21-alpine as build

ADD . /project
WORKDIR /project

ARG JAR_FILE=app.jar
RUN gradle build -x test
RUN ["sh", "-c", "jar -xf practice8-service-discovery/build/libs/${JAR_FILE}"]
RUN jdeps --ignore-missing-deps -q \
    --recursive \
    --multi-release 21 \
    --print-module-deps \
    --class-path 'BOOT-INF/lib/*' \
    practice8-service-discovery/build/libs/${JAR_FILE} > deps.info

RUN jlink \
    --add-modules $(cat deps.info) \
    --strip-debug \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output /myjre

FROM alpine:latest

ARG JAR_FILE=app.jar
RUN mkdir /java
ENV JAVA_HOME /java
ENV PATH $JAVA_HOME/bin:$PATH

COPY --from=build /myjre $JAVA_HOME
COPY --from=build /project/practice8-service-discovery/build/libs/$JAR_FILE /libs/
COPY --from=build /project/practice8-service-discovery/build/resources /resources/

CMD ["java", "-jar", "libs/app.jar"]
