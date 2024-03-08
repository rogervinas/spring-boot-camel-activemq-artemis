package com.tcmpartners.poc;

import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.lifecycle.Startable;

import java.io.File;

import static org.testcontainers.containers.wait.strategy.Wait.forLogMessage;

public class DockerComposeHelper {

  private static final String ACTIVEMQ = "activemq";
  private static final int ACTIVEMQ_PORT = 61616;

  private static Startable container = null;

  private static boolean isCI() {
    return "true".equals(System.getenv("CI"));
  }

  public static Startable createContainer() {
    if (isCI()) {
      return createContainerV1();
    } else {
      return createContainerV2();
    }
  }

  private static Startable createContainerV1() {
    if (container == null) {
      container = new DockerComposeContainer(new File("docker-compose.yml"))
        .withLocalCompose(true)
        .withExposedService(ACTIVEMQ, ACTIVEMQ_PORT)
        .waitingFor(ACTIVEMQ, forLogMessage(".*Server is now active.*", 1));
    }
    return container;
  }

  private static Startable createContainerV2() {
    if (container == null) {
      container = new ComposeContainer(new File("docker-compose.yml"))
        .withLocalCompose(true)
        .withExposedService(ACTIVEMQ, ACTIVEMQ_PORT)
        .waitingFor(ACTIVEMQ, forLogMessage(".*Server is now active.*", 1));
    }
    return container;
  }

  public static void setSystemProperties() {
    if (isCI()) {
      setSystemPropertiesV1();
    } else {
      setSystemPropertiesV2();
    }
  }

  private static void setSystemPropertiesV1() {
    DockerComposeContainer containerV1 = (DockerComposeContainer) container;
    var activeMqPort = containerV1.getServicePort(ACTIVEMQ, ACTIVEMQ_PORT);
    System.setProperty("activemq.port", activeMqPort.toString());
  }

  private static void setSystemPropertiesV2() {
    ComposeContainer containerV1 = (ComposeContainer) container;
    var activeMqPort = containerV1.getServicePort(ACTIVEMQ, ACTIVEMQ_PORT);
    System.setProperty("activemq.port", activeMqPort.toString());
  }
}
