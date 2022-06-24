package com.codegym.service.transaction;

import com.codegym.model.Transaction;
import com.codegym.service.IGeneralService;

import java.time.LocalDateTime;

public interface ITransactionService extends IGeneralService<Transaction> {
    Iterable<Transaction> findAllByCreatedDateBetween(LocalDateTime fromTime, LocalDateTime toTime);
}
