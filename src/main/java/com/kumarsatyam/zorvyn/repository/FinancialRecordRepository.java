package com.kumarsatyam.zorvyn.repository;

import com.kumarsatyam.zorvyn.model.FinancialRecord;
import com.kumarsatyam.zorvyn.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
    List<FinancialRecord> findByDateBetweenOrderByDateDesc(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.type = :type")
    BigDecimal sumAmountByType(@Param("type") TransactionType type);

    @Query("SELECT f.category, SUM(f.amount) FROM FinancialRecord f WHERE f.type = :type GROUP BY f.category")
    List<Object[]> sumAmountByCategoryAndType(@Param("type") TransactionType type);
    
    List<FinancialRecord> findTop5ByOrderByDateDesc();
}
