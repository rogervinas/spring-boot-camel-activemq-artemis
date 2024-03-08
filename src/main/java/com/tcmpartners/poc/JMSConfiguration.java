package com.tcmpartners.poc;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;

@Configuration
public class JMSConfiguration {

  @Bean
  public ConnectionFactory jmsConnectionFactory(
    @Value("${activemq.brokerURL}") String brokerURL,
    @Value("${activemq.username}") String username,
    @Value("${activemq.password}") String password
  ) throws Exception {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    connectionFactory.setBrokerURL(brokerURL);
    return userCredentialsConnectionFactory(username, password, connectionFactory);
  }

  private ConnectionFactory userCredentialsConnectionFactory(
    String username,
    String password,
    ConnectionFactory connectionFactory
  ) {
    UserCredentialsConnectionFactoryAdapter connectionFactoryAdapter = new UserCredentialsConnectionFactoryAdapter();
    connectionFactoryAdapter.setUsername(username);
    connectionFactoryAdapter.setPassword(password);
    connectionFactoryAdapter.setTargetConnectionFactory(connectionFactory);
    return connectionFactoryAdapter;
  }
}
