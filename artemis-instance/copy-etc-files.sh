#!/bin/zsh

#docker exec -it spring-boot-camel-activemq-artemis-activemq-1 ls -1 /var/lib/artemis-instance/etc

docker cp spring-boot-camel-activemq-artemis-activemq-1:/var/lib/artemis-instance/etc/artemis.profile etc
docker cp spring-boot-camel-activemq-artemis-activemq-1:/var/lib/artemis-instance/etc/artemis-roles.properties etc
docker cp spring-boot-camel-activemq-artemis-activemq-1:/var/lib/artemis-instance/etc/artemis-users.properties etc
docker cp spring-boot-camel-activemq-artemis-activemq-1:/var/lib/artemis-instance/etc/bootstrap.xml etc
docker cp spring-boot-camel-activemq-artemis-activemq-1:/var/lib/artemis-instance/etc/broker.xml etc
docker cp spring-boot-camel-activemq-artemis-activemq-1:/var/lib/artemis-instance/etc/jolokia-access.xml etc
docker cp spring-boot-camel-activemq-artemis-activemq-1:/var/lib/artemis-instance/etc/log4j2.properties etc
docker cp spring-boot-camel-activemq-artemis-activemq-1:/var/lib/artemis-instance/etc/login.config etc
docker cp spring-boot-camel-activemq-artemis-activemq-1:/var/lib/artemis-instance/etc/management.xml etc
