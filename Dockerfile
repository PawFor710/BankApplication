FROM eclipse-temurin:17-jre-alpine
EXPOSE 8080
ADD /build/libs/BankApplication-0.0.1-SNAPSHOT.jar bankapp.jar
ENTRYPOINT ["java", "-jar", "bankapp.jar"]