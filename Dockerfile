FROM tomcat:jdk11-openjdk
VOLUME /tmp
EXPOSE 8080
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
COPY target/AIPOS2-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/app.war
ENV DATABASE_URL=jdbc:postgresql://postgres2:5432/testdb
ENV DATABASE_USERNAME=postgres
ENV DATABASE_PASSWORD=root
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.datasource.url=${DATABASE_URL}","-Dspring.datasource.username=${DATABASE_USERNAME}", "-Dspring.datasource.password=${DATABASE_PASSWORD}","-Dspring.profiles.active=dev", "-jar", "/usr/local/tomcat/webapps/app.war"]
