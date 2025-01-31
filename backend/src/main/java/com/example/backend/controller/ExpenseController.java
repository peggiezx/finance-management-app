package com.example.backend.controller;

import com.example.backend.model.Expense;
import com.example.backend.repository.ExpenseRepository;
import com.example.backend.service.ExpenseService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ExpenseRepository expenseRepository;

    public ExpenseController(ExpenseService expenseService, ExpenseRepository expenseRepository) {
        this.expenseService = expenseService;
        this.expenseRepository = expenseRepository;
    }

    // CREATE: POST /api/expenses
    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        Expense saved = expenseRepository.save(expense);
        System.out.println(saved);
        return expenseService.createExpense(expense);
    }

    // READ: GET /api/expenses
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/user/{userId}")
    public List<Expense> getExpensesByUser(Long userId) {
        return expenseService.getExpensesByUser(userId);
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(Long id) {
        return expenseService.getExpenseById(id);
    }

    // UPDATE: PUT /api/expenses/{id}
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        return expenseService.updateExpense(id, expense);
    }

    // DELETE: DELETE /api/expenses/{id}
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }

}
