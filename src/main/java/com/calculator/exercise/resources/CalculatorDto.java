package com.calculator.exercise.resources;

import java.math.BigDecimal;

public class CalculatorDto {

    private BigDecimal number;
    private long milliseconds;

    public CalculatorDto() {
    }

    public CalculatorDto(BigDecimal number, long milliseconds) {
        this.number = number;
        this.milliseconds = milliseconds;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public long getTime() {
        return milliseconds;
    }

    public void setTime(long time) {
        this.milliseconds = time;
    }
}
