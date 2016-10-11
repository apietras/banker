package com.pietras.service;

import com.pietras.model.Account;
import org.springframework.stereotype.Service;

/**
 * Created by aniapietras on 11.10.2016.
 *
 */

@Service
public interface AccountService {

    Account createAccount(Account account);

    Account getAccount(String id);

    Account updateAccount(Account account);
}
