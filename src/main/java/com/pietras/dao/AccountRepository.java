package com.pietras.dao;

import com.pietras.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by aniapietras on 11.10.2016.
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
}
