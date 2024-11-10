FROM gradle:8.7-jdk21-alpine AS build

# Копируем проект и устанавливаем рабочую директорию
ADD . /project
WORKDIR /project

# Переменная для имени JAR-файла
ARG JAR_FILE=practice8-api-gateway.jar

# Сборка проекта без тестов
RUN gradle build -x test

# Используем стандартный образ OpenJDK вместо кастомного JRE
FROM eclipse-temurin:21-jdk-alpine

# Создаём директории и копируем артефакты
RUN mkdir /app
WORKDIR /app
COPY --from=build /project/build/libs/$JAR_FILE /app/
COPY --from=build /project/build/resources /app/resources/

# Запускаем приложение
CMD ["java", "-Djavax.net.debug=ssl:handshake", "-jar", "/app/practice8-api-gateway.jar"]
