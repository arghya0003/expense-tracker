package com.expensetracker.config;

import com.expensetracker.model.Expense;
import com.expensetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ExpenseRepository expenseRepository;

    @Override
    public void run(String... args) {
        expenseRepository.save(Expense.builder()
                .title("Grocery Shopping")
                .amount(new BigDecimal("850.00"))
                .category("Food")
                .date(LocalDate.now())
                .description("Weekly groceries from DMart")
                .build());

        expenseRepository.save(Expense.builder()
                .title("Uber Ride")
                .amount(new BigDecimal("220.50"))
                .category("Transport")
                .date(LocalDate.now().minusDays(1))
                .description("Office commute")
                .build());

        expenseRepository.save(Expense.builder()
                .title("Netflix Subscription")
                .amount(new BigDecimal("649.00"))
                .category("Entertainment")
                .date(LocalDate.now().minusDays(3))
                .build());

        System.out.println("✓ Sample expenses loaded into database");
    }
}
