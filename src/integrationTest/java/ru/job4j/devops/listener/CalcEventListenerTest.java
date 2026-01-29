package ru.job4j.devops.listener;

import java.time.Duration;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import ru.job4j.devops.core.AbstractKafkaTestContainer;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.CalcEventRepository;
import ru.job4j.devops.repository.UserRepository;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest
public class CalcEventListenerTest extends AbstractKafkaTestContainer {

  @Autowired
  private KafkaTemplate<String, Object> kafkaTemplate;

  @Autowired
  private CalcEventRepository calcEventRepository;

  @Autowired
  private UserRepository userRepository;

  @Test
  void whenSendCalcEventThenItIsSaved() {
    User user = userRepository.findByUsername("testuser")
        .orElseGet(() -> {
          User newUser = new User();
          newUser.setUsername("testuser");
          return userRepository.save(newUser);
        });
    CalcEvent event = CalcEvent.builder()
        .user(user)
        .first(10)
        .second(20)
        .result(30)
        .type("СЛОЖЕНИЕ")
        .createDate(LocalDateTime.now())
        .build();

    kafkaTemplate.send("calculate", event);

    await()
        .pollInterval(Duration.ofSeconds(2))
        .atMost(10, SECONDS)
        .untilAsserted(() -> {
          var events = calcEventRepository.findAll();
          assertThat(events).isNotEmpty();
          var savedEventOptional = events.stream()
              .filter(e -> e.getUser() != null
                  && e.getUser().getId().equals(user.getId())
                  && e.getFirst() == 10
                  && e.getSecond() == 20
                  && e.getResult() == 30)
              .findFirst();
          assertThat(savedEventOptional)
              .isPresent()
              .hasValueSatisfying(savedEvent -> {
                assertThat(savedEvent.getId()).isNotNull();
                assertThat(savedEvent.getType()).isEqualTo("СЛОЖЕНИЕ");
                assertThat(savedEvent.getUser().getUsername()).isEqualTo("testuser");
              });
        });
  }
}
