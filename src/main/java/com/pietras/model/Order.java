package com.pietras.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by aniapietras on 11.10.2016.
 *
 * This class represents disposition for bank to transfer money from one account to another.
 * It has optional flag to say if it’s recurring order (with one day period) or not.
 */

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private Account source;

    @ManyToOne
    @JoinColumn(name = "target_account_id")
    private Account target;

    private String description;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal amount;

    @NotNull
    private Boolean recurring;


}
