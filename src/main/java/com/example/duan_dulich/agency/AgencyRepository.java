package com.example.duan_dulich.agency;

import com.example.duan_dulich.agency.entity.AgencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<AgencyEntity, Integer> {

    void deleteById(Integer id);
}
