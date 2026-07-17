package com.example.accountservice.Repository;

import com.example.accountservice.Entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface AccountRepo extends JpaRepository<AccountEntity,Long> {
    List<AccountEntity> findByUserId(Long userId);
    Optional<AccountEntity> findByAccountNo(String accountNo);
}
