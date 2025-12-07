package com.rogervinas.poc;

import org.testcontainers.containers.ComposeContainer;

import java.io.File;

import static org.testcontainers.containers.wait.strategy.Wait.forLogMessage;

public class DockerComposeHelper {

  private static final String ACTIVEMQ = "activemq";
  private static final int ACTIVEMQ_PORT = 61616;

  public static ComposeContainer createContainer() {
    return new ComposeContainer(new File("docker-compose.yml"))
      .withExposedService(ACTIVEMQ, ACTIVEMQ_PORT)
      .waitingFor(ACTIVEMQ, forLogMessage(".*Server is now active.*", 1));
  }

  public static void setSystemProperties(ComposeContainer container) {
    var activeMqPort = container.getServicePort(ACTIVEMQ, ACTIVEMQ_PORT);
    System.setProperty("activemq.port", activeMqPort.toString());
  }
}
