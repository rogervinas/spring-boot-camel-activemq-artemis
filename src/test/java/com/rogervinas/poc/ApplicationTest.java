package com.rogervinas.poc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest
@Testcontainers
@ExtendWith(OutputCaptureExtension.class)
class ApplicationTest {

  @Container
  static ComposeContainer container = DockerComposeHelper.createContainer();

  @BeforeAll
  static void beforeAll() {
    DockerComposeHelper.setSystemProperties(container);
  }

  @Test
  void shouldProduceAndConsumeMessages(CapturedOutput output) {
    await().atMost(Duration.ofSeconds(30)).untilAsserted(() -> {
      assertThat(output.getOut()).contains("PRODUCE Hello 1");
      assertThat(output.getOut()).contains("CONSUME Hello 1");
    });
  }
}
