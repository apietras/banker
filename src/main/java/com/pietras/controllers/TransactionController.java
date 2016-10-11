package com.pietras.controllers;

import com.pietras.model.Transaction;
import com.pietras.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Created by aniapietras on 11.10.2016.
 */

@RestController
@RequestMapping("/account")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "/{id}/transaction", method = RequestMethod.GET)
    ResponseEntity<List<Transaction>> get(@PathVariable("id") String id,
                                          @RequestParam(value = "from", required = false) Optional<Long> from,
                                          @RequestParam(value = "to", required = true) Optional<Long> to) {
        Optional<Instant> fromInstant = from.map(timestamp -> Instant.ofEpochMilli(timestamp));
        Optional<Instant> toInstant = to.map(timestamp -> Instant.ofEpochMilli(timestamp));
        List<Transaction> transactions = transactionService.getTransactionsForAccount(id, fromInstant, toInstant);
        return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
    }

}
