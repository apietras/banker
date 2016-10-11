package com.pietras.service;

import com.pietras.model.Transaction;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Created by aniapietras on 11.10.2016.
 */
public interface TransactionService {

    Transaction createTransaction(Transaction transaction);

    List<Transaction> getTransactionsForAccount(String accountId, Optional<Instant> from, Optional<Instant> to);

}
