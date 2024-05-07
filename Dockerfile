FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY E-Commerce_backEnd/e_commerce/target/*.jar app.jar

CMD java -jar app.jar