package ru.job4j.devops.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Модель данных для представления результата вычислений.
 * <p>
 * Используется для сериализации результатов арифметических операций в JSON.
 * Содержит одно поле - числовое значение результата.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {
  private double value;
}
