package ru.job4j.devops.service.impl;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.devops.models.Result;
import ru.job4j.devops.repository.ResultRepository;
import ru.job4j.devops.service.ResultService;

@Service
@AllArgsConstructor
public class ResultDbService implements ResultService {
  private final ResultRepository resultRepository;

  @Override
  public void save(Result result) {
    resultRepository.save(result);
  }

  @Override
  public List<Result> findAll() {
    return resultRepository.findAll();
  }
}
