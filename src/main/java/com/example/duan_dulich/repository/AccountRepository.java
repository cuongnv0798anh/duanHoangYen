package com.example.duan_dulich.repository;

import com.example.duan_dulich.model.entity.Account;
import com.example.duan_dulich.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    Optional<Account> findById(Integer Id);
    Account getByIdAccount(Integer id);
    Account findAccountByUserName(String userName);
    Boolean existsAccountByUserName(String  userName);
    Boolean existsByIdAccount(Integer id);
    List<Account> getAllByRoles(Role role);
    @Query(value = "select count(role.id_role) from account join account_role on account.id_account = account_role.account_id join role on role.id_role = account_role.role_id where role.id_role = ?1 ", nativeQuery = true)
    int getSum(Integer id);
    @Query(value = "select count(role.id_role) from account join account_role on account.id_account = account_role.account_id join role on role.id_role = account_role.role_id", nativeQuery = true)
    int getSumAll();

}

