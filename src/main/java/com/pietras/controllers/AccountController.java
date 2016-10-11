package com.pietras.controllers;

import com.pietras.model.Account;
import com.pietras.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * Created by aniapietras on 11.10.2016.
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Account> create(@Valid @RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return new ResponseEntity<Account>(createdAccount, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/balance", method = RequestMethod.GET)
    ResponseEntity<BigDecimal> getAccountBalance(@PathVariable("id") String id) {
        BigDecimal balance = accountService.getAccount(id).getBalance();
        return new ResponseEntity<BigDecimal>(balance, HttpStatus.OK);
    }
}
