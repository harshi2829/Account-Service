package com.example.accountservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import java.math.BigDecimal;


@Entity
@Table(name="accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Enumerated(EnumType.STRING)
    private  AccountType accountType;

    private  String  accountNo;
    private BigDecimal balance;
    private  long userId;
    private LocalDateTime createdAt;
}
