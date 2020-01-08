package com.calculator.exercise.services;

import com.calculator.exercise.resources.CalculatorDto;

import java.math.BigDecimal;

public interface CalculatorService {

    BigDecimal calculator(long fromNumber, long toNumber);

    CalculatorDto concurrentCalculator(long max, int threadCount) throws InterruptedException;
}
