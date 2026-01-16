package ru.job4j.devops.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Модель данных для представления двух чисел.
 * <p>
 * Используется как входной параметр для арифметических операций.
 * Содержит два числовых поля, которые передаются в HTTP запросе.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwoArgs {
    private double first;
    private double second;
}
