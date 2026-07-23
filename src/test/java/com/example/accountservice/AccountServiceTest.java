package com.example.accountservice;
import com.example.accountservice.Entity.AccountEntity;
import com.example.accountservice.Entity.AccountType;
import com.example.accountservice.Exception.AccountNotFoundException;
import com.example.accountservice.Exception.InsufficientFundsException;
import com.example.accountservice.Repository.AccountRepo;
import com.example.accountservice.Service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    AccountRepo repo;

    @InjectMocks
    AccountService accountService;

    AccountEntity account;

    @BeforeEach
    void setUp()
    {
        account = new AccountEntity();
        account.setAccountNo("ACC123");
        account.setUserId(1L);
        account.setBalance(new BigDecimal("1000"));
        account.setAccountType(AccountType.SAVINGS);
    }

    @Test
    void getBalance()
    {
        when(repo.findByAccountNo("ACC123")).thenReturn(Optional.of(account));
        AccountEntity result=accountService.getBalance("ACC123");

        assertNotNull(result);
        assertEquals("ACC123",result.getAccountNo());
    }

    @Test
    void getBalance_AccountNotFound()
    {
        when(repo.findByAccountNo("ACC123")).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class,()->accountService.getBalance("ACC123"));
    }

    @Test
    void getAccountsByUserId_Success()
    {
        when(repo.findByUserId(1L)).thenReturn(List.of(account));

        List<AccountEntity>result=accountService.getAccountsByUserId(1L);
        assertEquals(1,result.size());
    }

    @Test
    void updatBalance_Deposit_Success()
    {
        when(repo.findByAccountNo("ACC123")).thenReturn(Optional.of(account));
        when(repo.save(any(AccountEntity.class))).thenReturn(account);

        AccountEntity result=accountService.updateBalance("ACC123",new BigDecimal("500"),"DEPOSIT");

        assertNotNull(result);
        assertEquals(new BigDecimal("1500"),result.getBalance());
    }

    @Test
    void updateBalance_WithDraw_Sucess()
    {

        when(repo.findByAccountNo("ACC123")).thenReturn(Optional.of(account));
        when(repo.save(any(AccountEntity.class))).thenReturn(account);

        AccountEntity result=accountService.updateBalance("ACC123",new BigDecimal("500"),"WITHDRAW");

        assertNotNull(result);
        assertEquals(new BigDecimal("500"),result.getBalance());
    }

    @Test
    void updateBalance_InsufficientFunds_ThrowsException()
    {
        when(repo.findByAccountNo("ACC123")).thenReturn(Optional.of(account));

        assertThrows(InsufficientFundsException.class,
                ()->accountService.updateBalance("ACC123",new BigDecimal("2000"),"WITHDRAW"));
    }
}
