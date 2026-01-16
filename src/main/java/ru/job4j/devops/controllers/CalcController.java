package ru.job4j.devops.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.devops.models.Result;
import ru.job4j.devops.models.TwoArgs;

/**
 * REST контроллер для выполнения арифметических операций.
 * <p>
 * Обрабатывает HTTP запросы к эндпоинтам /calc/*
 * Все методы принимают и возвращают данные в формате JSON.
 * </p>
 */
@RestController
@RequestMapping("calc")
public class CalcController {

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
     * @see Result
     */
    @PostMapping("summarise")
    public ResponseEntity<Result> summarise(@RequestBody TwoArgs twoArgs) {
        var result = twoArgs.getFirst() + twoArgs.getSecond();
        return ResponseEntity.ok(new Result(result));
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
     * @see Result
     */
    @PostMapping("times")
    public ResponseEntity<Result> times(@RequestBody TwoArgs twoArgs) {
        var result = twoArgs.getFirst() * twoArgs.getSecond();
        return ResponseEntity.ok(new Result(result));
    }
}
