package com.example.duan_dulich.repository;

import com.example.duan_dulich.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByNameRole(String nameRole);
}
