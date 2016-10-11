package com.pietras.service.impl;

import com.pietras.dao.OrderRepository;
import com.pietras.model.Order;
import com.pietras.model.Transaction;
import com.pietras.service.AccountService;
import com.pietras.service.OrderService;
import com.pietras.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Created by aniapietras on 11.10.2016.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @Override
    @Transactional
    public Order createOrder(Order order) {
        BigDecimal currentBalance = accountService.getAccount(order.getSource().getId()).getBalance();
        if (currentBalance.compareTo(order.getAmount()) < 0) {
            throw new IllegalStateException("Account balance is too low to perform operation");
        }
        order.setId(UUID.randomUUID().toString());
        Order savedOrder = orderRepository.save(order);
        createOrderTransactions(savedOrder);
        return savedOrder;

    }

    @Transactional
    public void createOrderTransactions(Order order) {
        Transaction transactionSent = Transaction.builder()
                .account(order.getSource())
                .amount(order.getAmount().negate())
                .description("Order " + order.getId() + ": " + order.getDescription())
                .order(order)
                .date(Instant.now())
                .build();
        transactionService.createTransaction(transactionSent);

        Transaction transactionReceived = Transaction.builder()
                .account(order.getTarget())
                .amount(order.getAmount())
                .description("Order " + order.getId() + ": " + order.getDescription())
                .order(order)
                .date(Instant.now())
                .build();
        transactionService.createTransaction(transactionReceived);
    }

    @Transactional
    @Scheduled(cron = "1 0 0 * * *")
    public void executeRecurrentOrder() {

        List<Order> recurrentOrders = orderRepository.findByRecurringTrue();
        recurrentOrders.forEach(this::createOrderTransactions);

    }
}