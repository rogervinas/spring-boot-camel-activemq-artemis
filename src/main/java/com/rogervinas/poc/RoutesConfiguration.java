package com.rogervinas.poc;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfiguration {

  @Bean
  public RouteBuilder consumerRouteBuilder(
    @Value("${application.uri-consumer}") String uriFrom
  ) {
    return new RouteBuilder() {
      @Override
      public void configure() {
        from(uriFrom)
          .log("CONSUME ${body}");
      }
    };
  }

  @Bean
  public RouteBuilder producerRouteBuilder(
    @Value("${application.uri-producer}") String uriTo
  ) {
    return new RouteBuilder() {
      Integer counter = 1;

      @Override
      public void configure() {
        from("timer:producer?period=5000")
          .setBody(e -> "Hello " + counter++)
          .log("PRODUCE ${body}")
          .to(uriTo);
      }
    };
  }
}
