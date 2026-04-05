package com.kumarsatyam.zorvyn.service;

import com.kumarsatyam.zorvyn.dto.CreateRecordRequest;
import com.kumarsatyam.zorvyn.model.FinancialRecord;
import com.kumarsatyam.zorvyn.model.User;
import com.kumarsatyam.zorvyn.repository.FinancialRecordRepository;
import com.kumarsatyam.zorvyn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialRecordService {

    private final FinancialRecordRepository recordRepository;
    private final UserRepository userRepository;

    public FinancialRecord createRecord(CreateRecordRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElse(null);

        java.time.LocalDateTime recordDate = request.getDate() != null ? request.getDate() : java.time.LocalDateTime.now();

        FinancialRecord record = FinancialRecord.builder()
                .amount(request.getAmount())
                .type(request.getType())
                .category(request.getCategory())
                .date(recordDate)
                .notes(request.getNotes())
                .createdBy(user)
                .build();

        return recordRepository.save(record);
    }

    public List<FinancialRecord> getAllRecords() {
        return recordRepository.findAll();
    }

    public FinancialRecord getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }

    public FinancialRecord updateRecord(Long id, CreateRecordRequest request) {
        FinancialRecord record = getRecordById(id);
        
        java.time.LocalDateTime recordDate = request.getDate() != null ? request.getDate() : record.getDate();

        record.setAmount(request.getAmount());
        record.setType(request.getType());
        record.setCategory(request.getCategory());
        record.setDate(recordDate);
        record.setNotes(request.getNotes());
        
        return recordRepository.save(record);
    }

    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
    }
}
