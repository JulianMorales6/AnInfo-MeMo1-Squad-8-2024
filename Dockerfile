FROM gradle:jdk11 AS build
COPY . .
RUN ./gradlew build

FROM amazoncorretto:11-alpine-jdk
COPY --from=build /build/libs/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","demo.jar" ]
