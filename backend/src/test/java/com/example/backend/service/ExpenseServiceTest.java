package com.example.backend.service;

import com.example.backend.model.Expense;
import com.example.backend.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
// static imports for mockito given/when/then if you like BDD style
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

public class ExpenseServiceTest {

    private ExpenseRepository expenseRepository;
    private ExpenseService expenseService;
    private UserService userService;

    @BeforeEach
    void setUp() {
        expenseRepository = Mockito.mock(ExpenseRepository.class);
        expenseService = new ExpenseService(expenseRepository,userService);
    }

    @Test
    void testCreateExpense() {
        Expense expenseData = new Expense();
        expenseData.setCategory("Groceries");
        expenseData.setAmount(50.0);

        Expense savedMock = new Expense();
        savedMock.setCategory("Groceries");
        savedMock.setAmount(50.0);

        given(expenseRepository.save(expenseData)).willReturn(savedMock);

        Expense result = expenseService.createExpense(expenseData);

        assertThat(result.getCategory()).isEqualTo("Groceries");
        assertThat(result.getAmount()).isEqualTo(50.0);

        then(expenseRepository).should().save(expenseData);
    }

    @Test
    void updateExpense() {
        Expense existingExpense = new Expense();
        existingExpense.setCategory("Groceries");
        existingExpense.setAmount(50.0);

        Expense updatedExpense = new Expense();
        updatedExpense.setCategory("Entertainment");
        updatedExpense.setAmount(150.0);

        given(expenseRepository.findById(1L)).willReturn(Optional.of(existingExpense));

        Expense savedExpense = new Expense();
        savedExpense.setCategory("Entertainment");
        savedExpense.setAmount(150.0);

        given(expenseRepository.save(existingExpense)).willReturn(savedExpense);

        Expense result = expenseService.updateExpense(1L, updatedExpense);

        assertThat(result.getCategory()).isEqualTo("Entertainment");
        assertThat(result.getAmount()).isEqualTo(150.0);

        then(expenseRepository).should().save(existingExpense);
    }
}
