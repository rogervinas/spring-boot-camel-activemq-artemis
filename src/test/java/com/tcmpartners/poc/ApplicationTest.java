package com.tcmpartners.poc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.testcontainers.containers.wait.strategy.Wait.forLogMessage;

@SpringBootTest
@Testcontainers
@ExtendWith(OutputCaptureExtension.class)
class ApplicationTest {

  static final String ACTIVEMQ = "activemq";
  static final int ACTIVEMQ_PORT = 61616;

  @Container
  static ComposeContainer container = new ComposeContainer(new File("docker-compose.yml"))
    .withLocalCompose(true)
    .withExposedService(ACTIVEMQ, ACTIVEMQ_PORT)
    .waitingFor(ACTIVEMQ, forLogMessage(".*Server is now active.*", 1));

  @BeforeAll
  static void beforeAll() {
    var activeMqPort = container.getServicePort(ACTIVEMQ, ACTIVEMQ_PORT);
    System.setProperty("activemq.port", activeMqPort.toString());
  }

  @Test
  void shouldProduceAndConsumeMessages(CapturedOutput output) {
    await().atMost(Duration.ofSeconds(30)).untilAsserted(() -> {
      assertThat(output.getOut()).contains("PRODUCE Hello 1");
      assertThat(output.getOut()).contains("CONSUME Hello 1");
    });
  }
}
