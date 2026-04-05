package com.kumarsatyam.zorvyn.controller;

import com.kumarsatyam.zorvyn.dto.CreateRecordRequest;
import com.kumarsatyam.zorvyn.model.FinancialRecord;
import com.kumarsatyam.zorvyn.service.FinancialRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class FinancialRecordController {

    private final FinancialRecordService recordService;

    @PostMapping
    public ResponseEntity<FinancialRecord> createRecord(@Valid @RequestBody CreateRecordRequest request) {
        return new ResponseEntity<>(recordService.createRecord(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FinancialRecord>> getAllRecords() {
        return ResponseEntity.ok(recordService.getAllRecords());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialRecord> getRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(recordService.getRecordById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinancialRecord> updateRecord(@PathVariable Long id, @Valid @RequestBody CreateRecordRequest request) {
        return ResponseEntity.ok(recordService.updateRecord(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}
