package com.example.backend.service;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.Expense;
import com.example.backend.model.User;
import com.example.backend.repository.ExpenseRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    public ExpenseService(ExpenseRepository expenseRepository, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
    }

    // CREATE
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    // READ ALL
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // READ ALL from One user
    public List<Expense> getExpensesByUser(Long userId) {
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        if (expenses.isEmpty()) {
            throw new ResourceNotFoundException("Expense not found for user ID: "+ userId);
        }
        return expenses;
    }

    // READ ONE
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: "+ id));
    }

    // UPDATE
    public Expense updateExpense(Long id, Expense expenseData) {
        Expense existing = getExpenseById(id);

        if (expenseData.getUser() != null && expenseData.getUser().getId() != null) {
            User existingUser = userService.getUserById(expenseData.getUser().getId());
            existing.setUser(existingUser);
        }
        existing.setCategory(expenseData.getCategory());
        existing.setDescription(expenseData.getDescription());
        existing.setAmount(expenseData.getAmount());
        existing.setDate(expenseData.getDate());

        return expenseRepository.save(existing);
    }

    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Expense not found with id: "+id);
        }
        expenseRepository.deleteById(id);
    }
}
