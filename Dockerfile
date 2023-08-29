FROM amazoncorretto:17-alpine
WORKDIR /app
COPY /build/libs/ProjectGoods-1.0.0.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
