plugins {
  java
  id("org.springframework.boot") version "3.2.5"
  id("io.spring.dependency-management") version "1.1.5"
}

group = "com.tcmpartners"
version = "0.0.1-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
  mavenCentral()
}

val artemisVersion = "2.33.0"

dependencies {
  implementation(platform("org.apache.camel.springboot:camel-spring-boot-dependencies:4.5.0"))
  implementation("org.apache.camel.springboot:camel-spring-boot-starter")

  implementation("org.apache.camel.springboot:camel-jms-starter")
  implementation("org.apache.activemq:artemis-commons:$artemisVersion")
  implementation("org.apache.activemq:artemis-selector:$artemisVersion")
  implementation("org.apache.activemq:artemis-core-client:$artemisVersion")
  implementation("org.apache.activemq:artemis-jakarta-client:$artemisVersion")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.awaitility:awaitility:4.2.1")
  testImplementation("org.testcontainers:junit-jupiter:1.19.8")
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
