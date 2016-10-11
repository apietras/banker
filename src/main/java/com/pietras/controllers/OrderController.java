package com.pietras.controllers;

import com.pietras.model.Order;
import com.pietras.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by aniapietras on 11.10.2016.
 */
@RestController
@RequestMapping("/order")
@Api
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "create order")
    ResponseEntity<Order> create(@Valid @RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<Order>(createdOrder, HttpStatus.CREATED);
    }


}
