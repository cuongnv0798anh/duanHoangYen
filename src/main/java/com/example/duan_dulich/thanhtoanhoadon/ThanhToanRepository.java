package com.example.duan_dulich.thanhtoanhoadon;

import com.example.duan_dulich.thanhtoanhoadon.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThanhToanRepository extends JpaRepository<HoaDon, Integer> {

    void deleteById(Integer id);

    List<HoaDon> findAllByIdNguoiMua(Integer idNguoiMua);
}
