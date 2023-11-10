FROM openjdk:11

RUN apt-get update && apt-get install -y maven

COPY . /app

WORKDIR /app

RUN mvn clean package

EXPOSE 8080

CMD ["java", "-jar", "2-2-shaded.jar", "server", "run"]
