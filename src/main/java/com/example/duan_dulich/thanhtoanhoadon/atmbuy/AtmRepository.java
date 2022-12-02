package com.example.duan_dulich.thanhtoanhoadon.atmbuy;

import com.example.duan_dulich.thanhtoanhoadon.entity.AtmThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmRepository extends JpaRepository<AtmThanhToan, Integer> {
    AtmThanhToan getAtmThanhToanByMaAtmAndPassAtm(String maAtm, String passAtm);
}
