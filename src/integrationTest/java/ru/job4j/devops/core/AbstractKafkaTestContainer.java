package ru.job4j.devops.core;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

public abstract class AbstractKafkaTestContainer extends AbstractPostgresTestContainer {

  private static final KafkaContainer KAFKA = new KafkaContainer(
      DockerImageName.parse("apache/kafka:3.7.2")
  );

  @BeforeAll
  static void beforeAll() {
    KAFKA.start();
  }

  @DynamicPropertySource
  public static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.kafka.consumer.auto-offset-reset", () -> "earliest");
    registry.add("spring.kafka.bootstrap-servers", KAFKA::getBootstrapServers);
  }
}
