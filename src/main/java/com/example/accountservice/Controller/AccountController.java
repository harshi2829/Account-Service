package com.example.accountservice.Controller;

import com.example.accountservice.Entity.AccountEntity;
import com.example.accountservice.Entity.AccountType;
import com.example.accountservice.Service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@Tag(name="Account Controller",description = "APIs for user balance,update,Account")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    public AccountService service;


    @Operation(summary = "Balance", description = "Fetches a  user account balance")
    @GetMapping("/balance/{accountNo}")
    public  AccountEntity balance(@PathVariable String accountNo)
    { return service.getBalance(accountNo);
    }


    @Operation(summary = "User Details By Id", description = "Fetches user account with Id")
    @GetMapping("/{userId}")
    public List<AccountEntity> getAccounts(@PathVariable Long userId)
    {
        return service.getAccountsByUserId(userId);
    }



    @Operation(summary = "Creates a new user", description = "Creates a new user account with Account type")
    @PostMapping("/create")
    public AccountEntity create(@RequestParam Long userId,@RequestParam AccountType type)
    {
        return service.createAccount(userId,type);
    }


    @Operation(summary = "Updates user", description = "Updates user account")
    @PutMapping("/update-balance")
    public  AccountEntity updateBalance(@RequestParam String accountNo,
                                        @RequestParam BigDecimal amount,
                                        @RequestParam String operation)
    {
        return service.updateBalance(accountNo, amount, operation);
    }
}
