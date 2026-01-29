package ru.job4j.devops.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.devops.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findByUsername(String name);

}
