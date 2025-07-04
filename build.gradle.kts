import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED

plugins {
  java
  id("org.springframework.boot") version "3.5.3"
  id("io.spring.dependency-management") version "1.1.7"
  id("org.jlleitschuh.gradle.ktlint") version "12.3.0"
}

group = "com.rogervinas"
version = "0.0.1-SNAPSHOT"

repositories {
  mavenCentral()
}

val artemisVersion = "2.41.0"

dependencies {
  implementation(platform("org.apache.camel.springboot:camel-spring-boot-dependencies:4.12.0"))
  implementation("org.apache.camel.springboot:camel-spring-boot-starter")

  implementation("org.apache.camel.springboot:camel-jms-starter")
  implementation("org.apache.activemq:artemis-commons:$artemisVersion")
  implementation("org.apache.activemq:artemis-selector:$artemisVersion")
  implementation("org.apache.activemq:artemis-core-client:$artemisVersion")
  implementation("org.apache.activemq:artemis-jakarta-client:$artemisVersion")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.awaitility:awaitility:4.3.0")
  testImplementation("org.testcontainers:junit-jupiter:1.21.3")
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events(PASSED, SKIPPED, FAILED)
  }
}
