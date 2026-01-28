package ru.job4j.devops.service.impl;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.devops.models.CalcEvent;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.CalcEventRepository;
import ru.job4j.devops.repository.UserRepository;
import ru.job4j.devops.service.CalcService;

@Service
@RequiredArgsConstructor
public class CalcServiceImpl implements CalcService {

  private final UserRepository userRepository;
  private final CalcEventRepository calcEventRepository;

  @Override
  public boolean add(User user, int first, int second) {
    if (!userRepository.existsById(user.getId())) {
      return false;
    }
    int sum = first + second;
    CalcEvent calcEvent = CalcEvent.builder()
        .user(user)
        .first(first)
        .second(second)
        .result(sum)
        .type("СЛОЖЕНИЕ")
        .createDate(LocalDateTime.now())
        .build();
    calcEventRepository.save(calcEvent);
    return true;
  }
}
