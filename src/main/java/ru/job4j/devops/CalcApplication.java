package ru.job4j.devops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс Spring Boot приложения "Калькулятор".
 * Приложение предоставляет REST API для выполнения арифметических операций.
 */
@SpringBootApplication
public class CalcApplication {

	/**
	 * Точка входа в приложение.
	 * <p>
	 * Запускает Spring Boot приложение с конфигурацией по умолчанию.
	 * </p>
	 *
	 * @param args аргументы командной строки, могут содержать дополнительные
	 *             настройки Spring Boot
	 */
	public static void main(String[] args) {
		SpringApplication.run(CalcApplication.class, args);
	}
}
