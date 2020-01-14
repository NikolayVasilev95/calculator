package com.calculator.exercise.controllers;

import com.calculator.exercise.resources.CalculatorDto;
import com.calculator.exercise.services.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@Validated
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/api/calculator")
    public ResponseEntity<?> calculatorApi(@RequestParam @Positive @NotNull long number, @RequestParam @Positive @NotNull Integer threat) {
        try {
            CalculatorDto dto = calculatorService.concurrentCalculator(number, threat);
            return ResponseEntity.ok(dto);
        } catch (InterruptedException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }
}
