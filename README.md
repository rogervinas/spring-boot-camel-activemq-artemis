[![CI](https://github.com/rogervinas/spring-boot-camel-activemq-artemis/actions/workflows/ci.yml/badge.svg)](https://github.com/rogervinas/spring-boot-camel-activemq-artemis/actions/workflows/ci.yml)
![Java](https://img.shields.io/badge/Java-21-blue?labelColor=black)
![SpringBoot](https://img.shields.io/badge/SpringBoot-3.x-blue?labelColor=black)
![ApacheCamel](https://img.shields.io/badge/ApacheCamel-4.8.x-blue?labelColor=black)
![ActiveMQ-Artemis](https://img.shields.io/badge/ActiveMQ--Artemis-2.40.0-blue?labelColor=black)



# Spring Boot + Apache Camel + ActiveMQ Artemis

With SSL Dual Authentication 🚀

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
