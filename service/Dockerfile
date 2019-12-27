FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=db1
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/db1
ADD ./target/moviecruiserserver-0.0.1-SNAPSHOT.jar /usr/src/moviecruiserserver-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "moviecruiserserver-0.0.1-SNAPSHOT.jar"]
