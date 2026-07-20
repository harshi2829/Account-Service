package com.example.accountservice.Service;

import com.example.accountservice.Entity.AccountEntity;
import com.example.accountservice.Entity.AccountType;
import com.example.accountservice.Exception.AccountNotFoundException;
import com.example.accountservice.Exception.InsufficientFundsException;
import com.example.accountservice.Repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    public AccountRepo repo;

    public AccountEntity createAccount(Long userId, AccountType type)
    {
        AccountEntity account=new AccountEntity();
        account.setUserId(userId);
        account.setAccountType(type);
        account.setBalance(BigDecimal.ZERO);
        account.setCreatedAt(LocalDateTime.now());
        account.setAccountNo(
                UUID.randomUUID().toString());
        return  repo.save(account);
    }

    public AccountEntity  getBalance(String accountNo)
    {
        return repo.findByAccountNo(accountNo).orElseThrow(()->
                new AccountNotFoundException("Account Not Found"));
    }

    public List<AccountEntity> getAccountsByUserId(Long userId)
    {
        return  repo.findByUserId(userId);
    }


    public AccountEntity updateBalance(String accountNo, BigDecimal amount, String operation)
    {
        AccountEntity account=repo.findByAccountNo(accountNo).orElseThrow(()->
                new  AccountNotFoundException("Account Not Found"));
        if(operation.equals("DEPOSIT"))
        {
            account.setBalance(account.getBalance().add(amount));
        }
        else {
            if(account.getBalance().compareTo(amount) < 0)
            {
                throw new InsufficientFundsException("Insufficenit Amount ");
            }
            account.setBalance(account.getBalance().subtract(amount));
        }

        return repo.save(account);
    }
}
