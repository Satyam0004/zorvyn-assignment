package com.kumarsatyam.zorvyn.service;

import com.kumarsatyam.zorvyn.dto.DashboardSummaryResponse;
import com.kumarsatyam.zorvyn.model.FinancialRecord;
import com.kumarsatyam.zorvyn.model.TransactionType;
import com.kumarsatyam.zorvyn.repository.FinancialRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final FinancialRecordRepository recordRepository;

    public DashboardSummaryResponse getSummary() {
        BigDecimal totalIncome = recordRepository.sumAmountByType(TransactionType.INCOME);
        if (totalIncome == null) totalIncome = BigDecimal.ZERO;

        BigDecimal totalExpense = recordRepository.sumAmountByType(TransactionType.EXPENSE);
        if (totalExpense == null) totalExpense = BigDecimal.ZERO;

        BigDecimal netBalance = totalIncome.subtract(totalExpense);

        Map<String, BigDecimal> incomeByCategory = getCategoryMap(TransactionType.INCOME);
        Map<String, BigDecimal> expenseByCategory = getCategoryMap(TransactionType.EXPENSE);

        List<FinancialRecord> recentActivity = recordRepository.findTop5ByOrderByDateDesc();

        return new DashboardSummaryResponse(
                totalIncome,
                totalExpense,
                netBalance,
                incomeByCategory,
                expenseByCategory,
                recentActivity
        );
    }

    private Map<String, BigDecimal> getCategoryMap(TransactionType type) {
        List<Object[]> results = recordRepository.sumAmountByCategoryAndType(type);
        Map<String, BigDecimal> map = new HashMap<>();
        for (Object[] result : results) {
            String category = (String) result[0];
            BigDecimal sum = (BigDecimal) result[1];
            map.put(category, sum);
        }
        return map;
    }
}
