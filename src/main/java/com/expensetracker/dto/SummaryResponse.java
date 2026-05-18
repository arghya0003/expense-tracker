package com.expensetracker.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
public class SummaryResponse {
    private BigDecimal totalAmount;
    private int totalExpenses;
    private Map<String, BigDecimal> spentPerCategory;
}
