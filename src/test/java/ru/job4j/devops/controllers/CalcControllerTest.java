package ru.job4j.devops.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.devops.models.Result;
import ru.job4j.devops.models.ResultDto;
import ru.job4j.devops.models.TwoArgs;
import ru.job4j.devops.service.impl.ResultFakeService;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
class CalcControllerTest {

    @Test
    public void whenSummarizeOnePlusOneThenTwo() {
        var input = new TwoArgs(1, 1);
        var expected = new ResultDto(2);
        var output = new CalcController(new ResultFakeService()).summarise(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(output.getBody()).isEqualTo(expected);
    }

    @Test
    public void whenNegativeNumber() {
        var input = new TwoArgs(-1, -1);
        var expected = new ResultDto(-2);
        var output = new CalcController(new ResultFakeService()).summarise(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(output.getBody()).isEqualTo(expected);
    }

    @Test
    public void whenZeroPlusZero() {
        var input = new TwoArgs(0, 3);
        var expected = new ResultDto(3);
        var output = new CalcController(new ResultFakeService()).summarise(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(output.getBody()).isEqualTo(expected);
    }

    @Test
    public void whenTwoTimesTwoThenFour() {
        var input = new TwoArgs(2, 2);
        var expected = new ResultDto(4);
        var output = new CalcController(new ResultFakeService()).times(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(output.getBody()).isEqualTo(expected);
    }

    @Test
    public void whenZeroTimesZero() {
        var input = new TwoArgs(0, 0);
        var expected = new ResultDto(0);
        var output = new CalcController(new ResultFakeService()).times(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(output.getBody()).isEqualTo(expected);
    }

    @Test
    public void whenTimesNegatives() {
        var input = new TwoArgs(-3, -3);
        var expected = new ResultDto(9);
        var output = new CalcController(new ResultFakeService()).times(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(output.getBody()).isEqualTo(expected);
    }

    @Test
    public void whenNegativeNumberSecondTest() {
        var input = new TwoArgs(-1, -2);
        var expected = new ResultDto(-3);
        var output = new CalcController(new ResultFakeService()).summarise(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(output.getBody()).isEqualTo(expected);
    }

    @Test
    public void whenSummariseInDbOnePlusOneThenTwo() {
        var input = new TwoArgs(1, 1);
        var expected = new Result();
        expected.setResult(2D);
        var output = new CalcController(
            new ResultFakeService()
        ).summariseInDb(input);
        assertThat(output.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        var result = output.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getResult()).isEqualTo(expected.getResult());
    }
}