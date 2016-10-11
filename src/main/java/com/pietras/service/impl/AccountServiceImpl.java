package com.pietras.service.impl;

import com.pietras.dao.AccountRepository;
import com.pietras.model.Account;
import com.pietras.model.Transaction;
import com.pietras.service.AccountService;
import com.pietras.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * Created by aniapietras on 11.10.2016.
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    public static final String INITIAL_BALANCE_DESCRIPTION = "Initial account balance";

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionService transactionService;

    @Override
    @Transactional
    public Account createAccount(Account account) {

        BigDecimal initialBalance = account.getBalance();
        account.setId(UUID.randomUUID().toString());
        account.setBalance(new BigDecimal(0.0));
        Account savedAccount = accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .account(savedAccount)
                .amount(initialBalance)
                .description(INITIAL_BALANCE_DESCRIPTION)
                .date(Instant.now())
                .build();

        transactionService.createTransaction(transaction);
        return getAccount(savedAccount.getId());

    }

    @Override
    public Account getAccount(String id) {
        return accountRepository.findOne(id);
    }

    @Override
    public Account updateAccount(Account account) {
        return accountRepository.save(account);

    }
}
