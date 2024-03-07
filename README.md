# Spring Boot + Apache Camel + ActiveMQ Artemis

Based on https://github.com/apache/activemq-artemis/tree/2.30.0/examples/features/standard/ssl-enabled-dual-authentication

# Test
```
./gradlew check
```

# Run
```
docker compose up -d
./gradlew bootRun
docker compose down
```

ActiveMQ console at http://localhost:8161 (artemis/artemis)

# Build and Run Jar
```
docker compose up -d
./gradlew build
java -jar build/libs/spring-boot-camel-activemq-artemis-0.0.1-SNAPSHOT.jar
docker compose down
```
