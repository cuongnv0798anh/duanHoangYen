package com.example.duan_dulich.repository;

import com.example.duan_dulich.model.entity.Account;
import com.example.duan_dulich.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    Optional<Account> findById(Integer Id);
    Account getByIdAccount(Integer id);
    Account findAccountByUserName(String userName);
    Boolean existsAccountByUserName(String  userName);
    List<Account> getAllByRoles(Role role);
}

