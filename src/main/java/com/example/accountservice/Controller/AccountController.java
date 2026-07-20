package com.example.accountservice.Controller;

import com.example.accountservice.Entity.AccountEntity;
import com.example.accountservice.Entity.AccountType;
import com.example.accountservice.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    public AccountService service;

    @GetMapping("/balance/{accountNo}")
    public  AccountEntity balance(@PathVariable String accountNo)
    { return service.getBalance(accountNo);
    }

    @GetMapping("/{userId}")
    public List<AccountEntity> getAccounts(@PathVariable Long userId)
    {
        return service.getAccountsByUserId(userId);
    }

    @PostMapping("/create")
    public AccountEntity create(@RequestParam Long userId,@RequestParam AccountType type)
    {
        return service.createAccount(userId,type);
    }

    @PutMapping("/update-balance")
    public  AccountEntity updateBalance(@RequestParam String accountNo,
                                        @RequestParam BigDecimal amount,
                                        @RequestParam String operation)
    {
        return service.updateBalance(accountNo, amount, operation);
    }
}
