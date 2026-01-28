package ru.job4j.devops.service.impl;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import ru.job4j.devops.core.AbstractPostgresTestContainer;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.CalcEventRepository;
import ru.job4j.devops.repository.UserRepository;
import ru.job4j.devops.service.CalcService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class CalcServiceImplTest extends AbstractPostgresTestContainer {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CalcEventRepository calcEventRepository;

  @Autowired
  private CalcService calcService;

  private User testUser;

  @BeforeEach
  void setUp() {
    calcEventRepository.deleteAll();
    userRepository.deleteAll();
    testUser = User.builder()
        .username("testuser")
        .build();
    testUser = userRepository.save(testUser);
  }

  @Test
  void whenAddWithExistingUserThenSuccessAndEventSaved() {
    int first = 10;
    int second = 5;
    int expectedSum = 15;

    boolean result = calcService.add(testUser, first, second);

    assertTrue(result);

    List<CalcEvent> savedEvents = calcEventRepository.findAll();
    CalcEvent savedEvent = savedEvents.get(0);

    assertThat(savedEvents.size()).isEqualTo(1);
    assertThat(savedEvent.getUser().getId()).isEqualTo(testUser.getId());
    assertThat(savedEvent.getFirst()).isEqualTo(first);
    assertThat(savedEvent.getSecond()).isEqualTo(second);
    assertThat(savedEvent.getResult()).isEqualTo(expectedSum);
    assertThat(savedEvent.getType()).isEqualTo("СЛОЖЕНИЕ");
    assertThat(savedEvent.getCreateDate()).isNotNull();
  }

  @Test
  void whenAddWithNonExistingUserThenReturnFalse() {
    User nonExistingUser = User.builder()
        .id(999L)
        .username("ghost")
        .build();
    int first = 10;
    int second = 5;

    boolean result = calcService.add(nonExistingUser, first, second);
    assertFalse(result);
    List<CalcEvent> savedEvents = calcEventRepository.findAll();
    assertThat(savedEvents.size()).isEqualTo(0);
  }
}