package ru.job4j.devops.controllers;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.devops.models.Result;
import ru.job4j.devops.models.ResultDto;
import ru.job4j.devops.models.TwoArgs;
import ru.job4j.devops.service.ResultService;

/**
 * REST контроллер для выполнения арифметических операций.
 * <p>
 * Обрабатывает HTTP запросы к эндпоинтам /calc/*
 * Все методы принимают и возвращают данные в формате JSON.
 * </p>
 */
@RestController
@RequestMapping("calc")
@RequiredArgsConstructor
public class CalcController {

    private final ResultService resultService;

    /**
     * Выполняет операцию сложения двух чисел.
     * <p>
     * Принимает JSON объект с двумя числами и возвращает их сумму.
     * </p>
     *
     * @param twoArgs объект, содержащий два числа для сложения.
     *                Автоматически преобразуется из JSON.
     * @return {@link ResponseEntity} с результатом сложения в формате JSON
     *         и статусом HTTP 200 OK
     * @see TwoArgs
     * @see ResultDto
     */
    @PostMapping("summarise")
    public ResponseEntity<ResultDto> summarise(@RequestBody TwoArgs twoArgs) {
        var result = twoArgs.getFirst() + twoArgs.getSecond();
        return ResponseEntity.ok(new ResultDto(result));
    }

    /**
     * Выполняет операцию сложения двух чисел и сохраняет результат в базу данных.
     * <p>
     * Принимает JSON объект с двумя числами, вычисляет их сумму,
     * сохраняет информацию об операции в базу данных и возвращает
     * сохраненную сущность с полными данными об операции.
     * </p>
     *
     * @param twoArgs объект, содержащий два числа для сложения.
     *                Автоматически преобразуется из JSON.
     * @return {@link ResponseEntity} с сохраненной сущностью {@link Result},
     *         содержащей аргументы, результат операции, тип операции ("+"),
     *         дату создания и автоматически сгенерированный ID.
     *         Статус HTTP 200 OK
     * @see TwoArgs
     * @see Result
     * @see ResultService#save(Result)
     */
    @PostMapping("summarise/db")
    public ResponseEntity<Result> summariseInDb(@RequestBody TwoArgs twoArgs) {
        var result = new Result();
        result.setFirstArg(twoArgs.getFirst());
        result.setSecondArg(twoArgs.getSecond());
        result.setResult(twoArgs.getFirst() + twoArgs.getSecond());
        result.setOperation("+");
        result.setCreateDate(LocalDate.now());
        resultService.save(result);
        return ResponseEntity.ok(result);
    }

    /**
     * Возвращает историю всех выполненных операций.
     * <p>
     * Получает из базы данных список всех сохраненных операций
     * с полной информацией: аргументы, результаты, типы операций
     * и даты выполнения.
     * </p>
     *
     * @return {@link ResponseEntity} со списком всех сущностей {@link Result},
     *         хранящихся в базе данных. Каждый элемент списка содержит
     *         ID операции, аргументы, результат, тип операции и дату создания.
     *         Статус HTTP 200 OK
     * @see Result
     * @see ResultService#findAll()
     */
    @GetMapping("/")
    public ResponseEntity<List<Result>> logs() {
        return ResponseEntity.ok(resultService.findAll());
    }

    /**
     * Выполняет операцию умножения двух чисел.
     * <p>
     * Принимает JSON объект с двумя числами и возвращает их произведение.
     * </p>
     *
     * @param twoArgs объект, содержащий два числа для умножения.
     *                Автоматически преобразуется из JSON.
     * @return {@link ResponseEntity} с результатом умножения в формате JSON
     *         и статусом HTTP 200 OK
     * @see TwoArgs
     * @see ResultDto
     */
    @PostMapping("times")
    public ResponseEntity<ResultDto> times(@RequestBody TwoArgs twoArgs) {
        var result = twoArgs.getFirst() * twoArgs.getSecond();
        return ResponseEntity.ok(new ResultDto(result));
    }
}
