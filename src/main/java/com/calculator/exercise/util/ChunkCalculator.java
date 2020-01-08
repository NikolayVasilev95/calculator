package com.calculator.exercise.util;

import java.math.BigDecimal;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;

public class ChunkCalculator implements Callable<BigDecimal> {

    private BiFunction<Long, Long, BigDecimal> function;
    private long from;
    private long to;

    public ChunkCalculator(long from, long to, BiFunction<Long, Long, BigDecimal> function) {
        this.from = from;
        this.to = to;
        this.function = function;
    }

    @Override
    public BigDecimal call() throws Exception {
        return function.apply(from, to);
    }
}
