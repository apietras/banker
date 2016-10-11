package com.pietras.dao;

import com.pietras.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by aniapietras on 11.10.2016.
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, String> {

    List<Order> findByRecurringTrue();
}
