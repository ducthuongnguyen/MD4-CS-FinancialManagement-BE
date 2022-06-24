package com.codegym.controller;


import com.codegym.model.Transaction;
import com.codegym.service.transaction.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    public ITransactionService transactionService;

    @GetMapping
    public ResponseEntity<Iterable<Transaction>> findAllTransaction() {
        Iterable<Transaction> transactions = transactionService.findAllByOrderByCreatedDate();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Transaction> add(@RequestBody Transaction transaction) {
        transaction.setCreatedDate(LocalDateTime.now());
        return ResponseEntity.ok(transactionService.save(transaction));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.findById(id);
        if (!transaction.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transaction.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.findById(id);
        if (!transaction.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        transactionService.remove(id);
        return new ResponseEntity<>(transaction.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateProduct(@PathVariable Long id, @RequestBody Transaction transaction) {
        Optional<Transaction> transaction1 = transactionService.findById(id);
        if (!transaction1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        transaction.setId(transaction1.get().getId());
        transaction.setCreatedDate(LocalDateTime.now());
        transactionService.save(transaction);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}