package com.pietras.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

/**
 * Created by aniapietras on 11.10.2016.
 *
 * This entity represents basic account metadata with auto-generated id (we’re using this id as account number).
 * It contains only the id and current balance of the account.
 */

@Entity
@Data
@NoArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    private String id;

    @DecimalMin("0.0")
    private BigDecimal balance;

}
