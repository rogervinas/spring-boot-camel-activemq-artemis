package com.rogervinas.poc;

import org.testcontainers.containers.ComposeContainer;

import java.io.File;

import static org.testcontainers.containers.wait.strategy.Wait.forLogMessage;

public class DockerComposeHelper {

  private static final String ACTIVEMQ = "activemq";
  private static final int ACTIVEMQ_PORT = 61616;

  private static ComposeContainer container = null;

  public static ComposeContainer createContainer() {
    if (container == null) {
      container = new ComposeContainer(new File("docker-compose.yml"))
        .withLocalCompose(true)
        .withExposedService(ACTIVEMQ, ACTIVEMQ_PORT)
        .waitingFor(ACTIVEMQ, forLogMessage(".*Server is now active.*", 1));
    }
    return container;
  }

  public static void setSystemProperties() {
    var activeMqPort = container.getServicePort(ACTIVEMQ, ACTIVEMQ_PORT);
    System.setProperty("activemq.port", activeMqPort.toString());
  }
}
