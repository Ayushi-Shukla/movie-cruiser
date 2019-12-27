FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=db1
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/db1
ADD ./target/MovieCruiserAuthenticationService-0.0.1.jar /usr/src/MovieCruiserAuthenticationService-0.0.1.jar
ENTRYPOINT ["java", "-jar", "MovieCruiserAuthenticationService-0.0.1.jar"]
