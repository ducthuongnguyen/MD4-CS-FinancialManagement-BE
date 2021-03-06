package com.codegym.service.transaction;

import com.codegym.model.Transaction;
import com.codegym.model.Wallet;
import com.codegym.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    ITransactionRepository transactionRepository;

    @Override
    public Iterable<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void remove(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Iterable<Transaction> findAllByOrderByCreatedDate() {
        return transactionRepository.findAllByOrderByCreatedDateDesc();
    }

    @Override
    public Iterable<Transaction> findAllByCreatedDateBetween(LocalDateTime fromTime, LocalDateTime toTime) {
        return transactionRepository.findAllByCreatedDateBetween(fromTime, toTime);
    }

    @Override
    public Iterable<Transaction> findAllByWalletIdAndCreatedDateBetween(Long id, LocalDateTime fromTime, LocalDateTime toTime) {
        return transactionRepository.findAllByWalletIdAndCreatedDateBetween(id, fromTime, toTime);
    }

    @Override
    public Iterable<Transaction> findAllByWallet(Wallet wallet) {
        return transactionRepository.findAllByWallet(wallet);
    }

    @Override
    public Iterable<Transaction> findAllByWalletOrderByCreatedDateDesc(Wallet wallet) {
        return transactionRepository.findAllByWalletOrderByCreatedDateDesc(wallet);
    }


    @Override
    public Iterable<Transaction> findAllByCreatedDateBetween(Long walletId,LocalDateTime from, LocalDateTime to) {
        return transactionRepository.findAllByCreatedDateBetween(walletId,from,to);
    }
}
