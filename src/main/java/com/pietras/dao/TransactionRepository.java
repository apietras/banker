package com.pietras.dao;

import com.pietras.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * Created by aniapietras on 11.10.2016.
 */
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {

    public List<Transaction> findByAccountIdAndDateBetween(String accountId, Instant from, Instant to);

    public List<Transaction> findByAccountIdAndDateAfter(String accountId, Instant from);

    public List<Transaction> findByAccountIdAndDateBefore(String accountId, Instant to);

    public List<Transaction> findByAccountId(String accountId);

}
