[![CI](https://github.com/rogervinas/spring-boot-camel-activemq-artemis/actions/workflows/gradle.yml/badge.svg)](https://github.com/rogervinas/spring-boot-camel-activemq-artemis/actions/workflows/gradle.yml)

# Spring Boot + Apache Camel + ActiveMQ Artemis

Documentation:

* [JMS SSL Dual Authentication Example](https://github.com/apache/activemq-artemis/tree/2.30.0/examples/features/standard/ssl-enabled-dual-authentication)
* [Docker - Overriding files in etc folder](https://activemq.apache.org/components/artemis/documentation/latest/docker.html#overriding-files-in-etc-folder)

# Test
```shell
./gradlew check
```

# Run
```shell
docker compose up -d
./gradlew bootRun
docker compose down
```

ActiveMQ console at http://localhost:8161 (artemis/artemis)

# Build and Run Jar
```shell
docker compose up -d
./gradlew build
java -jar build/libs/spring-boot-camel-activemq-artemis-0.0.1-SNAPSHOT.jar
docker compose down
```
