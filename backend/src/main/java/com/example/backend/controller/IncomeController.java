package com.example.backend.controller;

import com.example.backend.model.Income;
import com.example.backend.repository.IncomeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/income")
public class IncomeController {

    private final IncomeRepository incomeRepository;

    public IncomeController(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @PostMapping
    public Income createIncome(@RequestBody Income income) {
        return incomeRepository.save(income);
    }

    @GetMapping
    public List<Income> getAllIncome() {
        return incomeRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Income> getAllIncomeByUserId(@PathVariable Long userId) {
        return incomeRepository.findByUserId(userId);
    }
}
