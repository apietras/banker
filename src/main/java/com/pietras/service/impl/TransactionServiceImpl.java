package com.pietras.service.impl;

import com.pietras.dao.TransactionRepository;
import com.pietras.model.Account;
import com.pietras.model.Transaction;
import com.pietras.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by aniapietras on 11.10.2016.
 */
@Service
public class TransactionServiceImpl implements TransactionService {


    @Autowired
    TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Transaction createTransaction(Transaction transaction) {

        transaction.setId(UUID.randomUUID().toString());
        Account transactionAccount = transaction.getAccount();
        BigDecimal currentBalance = transactionAccount.getBalance();
        BigDecimal newBalance = currentBalance.add(transaction.getAmount());
        transactionAccount.setBalance(newBalance);
        return transactionRepository.save(transaction);

    }

    @Override
    public List<Transaction> getTransactionsForAccount(String accountId, Optional<Instant> from, Optional<Instant> to) {
        if (from.isPresent() || to.isPresent()) {
            if (from.isPresent() && !to.isPresent()) {
                return transactionRepository.findByAccountIdAndDateAfter(accountId, from.get());
            }
            if (!from.isPresent() && to.isPresent()) {
                return transactionRepository.findByAccountIdAndDateBefore(accountId, to.get());
            }
            return transactionRepository.findByAccountIdAndDateBetween(accountId, from.get(), to.get());
        } else {
            return transactionRepository.findByAccountId(accountId);
        }
    }
}
