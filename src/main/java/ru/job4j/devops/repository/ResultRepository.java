package ru.job4j.devops.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.devops.models.Result;

public interface ResultRepository extends CrudRepository<Result, Integer> {
  List<Result> findAll();
}
