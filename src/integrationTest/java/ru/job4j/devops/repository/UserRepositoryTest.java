package ru.job4j.devops.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import ru.job4j.devops.core.AbstractPostgresTestContainer;
import ru.job4j.devops.models.User;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class UserRepositoryTest extends AbstractPostgresTestContainer {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void whenSaveUser() {
    var user = new User();
    user.setUsername("Job4j");
    userRepository.save(user);
    var foundUser = userRepository.findById(user.getId());
    assertThat(foundUser).isPresent();
    assertThat(foundUser.get().getUsername()).isEqualTo("Job4j");
  }
}