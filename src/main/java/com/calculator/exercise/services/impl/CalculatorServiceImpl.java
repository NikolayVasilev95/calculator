package com.calculator.exercise.services.impl;

import com.calculator.exercise.resources.CalculatorDto;
import com.calculator.exercise.services.CalculatorService;
import com.calculator.exercise.util.ChunkCalculator;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public BigDecimal calculator(long fromNumber, long toNumber) {
        BigDecimal sum = new BigDecimal(0);
        long fivePercent = (toNumber - fromNumber) / 10;
        int donePercentage = 0;
        for (long i = fromNumber; i <= toNumber; i++) {
            if(i % fivePercent == 0){
                donePercentage += 10;
                System.out.println("[ " + LocalDateTime.now() + "] " + "[" + Thread.currentThread().getName() + "] " + donePercentage + "% done");
            }
            sum = sum.add(new BigDecimal(i));
        }
        return sum;
    }

    @Override
    public CalculatorDto concurrentCalculator(long max, int threadCount) throws InterruptedException {
        long start = System.currentTimeMillis();
        long interval = max / threadCount;
        List<Callable<BigDecimal>> tasks = new ArrayList<>();
        long from = 0;
        long to = 0;
        while (to < max) {
            to = from + interval;
            if (to > max)
                to = max;
            tasks.add(new ChunkCalculator(from, to, this::calculator));
            from = to + 1;
        }
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount, new CustomizableThreadFactory("calculator-"));
        List<Future<BigDecimal>> futures = executorService.invokeAll(tasks);
        BigDecimal result = futures.stream().map(this::getFuture).reduce(BigDecimal::add).get();
        long end = System.currentTimeMillis();
        long time = end - start;
        executorService.shutdown();
        return new CalculatorDto(result, time);
    }

    private BigDecimal getFuture(Future<BigDecimal> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            if(ArithmeticException.class.isInstance(e.getCause())){
                throw new ArithmeticException(e.getMessage());
            } else {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

}
