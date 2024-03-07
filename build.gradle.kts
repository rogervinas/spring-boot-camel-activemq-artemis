plugins {
  java
  id("org.springframework.boot") version "2.7.18"
  id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.tcmpartners"
version = "0.0.1-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
  mavenCentral()
}

val artemisVersion = "2.32.0"

dependencies {
  implementation(platform("org.apache.camel.springboot:camel-spring-boot-dependencies:3.22.1"))
  implementation("org.apache.camel.springboot:camel-spring-boot-starter")

  implementation("org.apache.camel.springboot:camel-jms-starter")
  implementation("org.apache.activemq:artemis-commons:$artemisVersion")
  implementation("org.apache.activemq:artemis-selector:$artemisVersion")
  implementation("org.apache.activemq:artemis-core-client:$artemisVersion")
  implementation("org.apache.activemq:artemis-jms-client:$artemisVersion")
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events(
      org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
      org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
      org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
    )
  }
}
