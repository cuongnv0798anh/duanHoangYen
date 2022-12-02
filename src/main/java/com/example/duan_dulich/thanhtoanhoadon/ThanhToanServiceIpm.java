package com.example.duan_dulich.thanhtoanhoadon;

import com.example.duan_dulich.model.bo.ResponseCRUD;
import com.example.duan_dulich.model.entity.TourEntity;
import com.example.duan_dulich.repository.TourReponsitory;
import com.example.duan_dulich.thanhtoanhoadon.atmbuy.AtmService;
import com.example.duan_dulich.thanhtoanhoadon.dto.request.SearchAtmRequest;
import com.example.duan_dulich.thanhtoanhoadon.dto.request.ThanhToanRequest;
import com.example.duan_dulich.thanhtoanhoadon.entity.AtmThanhToan;
import com.example.duan_dulich.thanhtoanhoadon.entity.HoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ThanhToanServiceIpm implements ThanhToanService {

    @Autowired
    ThanhToanRepository thanhToanRepository;

    @Autowired
    AtmService atmService;

    @Autowired
    TourReponsitory tourReponsitory;

//    @Override
//    public ResponseCRUD getAgency() {
//        List<HoaDon>  listData=  thanhToanRepository.findAll();
//        return new ResponseCRUD(listData);
//    }

    @Override
    public ResponseCRUD getTourThanhToan(Integer idNguoiMua) {
        List<Object> result = new ArrayList<>();
        List<HoaDon> hoaDonList = thanhToanRepository.findAllByIdNguoiMua(idNguoiMua);
        for (HoaDon hoaDon: hoaDonList){
            Map<String, Object> data = new HashMap<>();
            data.put("hoaDon", hoaDon);
            data.put("thongTinTour", tourReponsitory.getById(hoaDon.getIdTour()));
            result.add(data);
        }
        return new  ResponseCRUD(result);
    }

    @Override
    public ResponseCRUD create(ThanhToanRequest request, boolean active) throws InterruptedException {
        Map<String, Object> result = new HashMap<>();
        try{
            Thread.sleep(1000);
            System.out.println("run set time out");
            // check thẻ
            SearchAtmRequest searchAtmRequest = new SearchAtmRequest();
            searchAtmRequest.setMaAtm(request.getMaAtm());
            searchAtmRequest.setPassAtm(request.getPassAtm());
            AtmThanhToan checkThe = atmService.getAtmByMaAndPass(searchAtmRequest);
            if (checkThe == null){
                System.out.println("thẻ không tồn tại");
                result.put("message", "Thông tin thẻ thanh toán không chính xác");
                result.put("status", "ERROR");
                return  new ResponseCRUD(result);
            } else if (checkThe != null){
                System.out.println("thẻ đã tồn tại");
                if (checkThe.getSoDu() < request.getTongSoTien()){
                    System.out.println("thẻ không đủ số dư");
                    result.put("status", "ERROR");
                    result.put("message", "Số dư không khả dụng");
                    return  new ResponseCRUD(result);
                }
            }
            if (!active){
                System.out.println(" alert");
                result.put("message", "Bạn có chắc chắn muốn thanh toán, số dư sau thanh toán của bạn là: "
                        + (checkThe.getSoDu()-request.getTongSoTien()) +" VNĐ");
                result.put("status", "CONFIRM");
                return new ResponseCRUD(result);
            }else if (active){
                System.out.println("set dữ liệu insert");
                for (int i = 0; i < request.getArrIdTour().length; i++){
                    System.out.println("run check id//"+request.getArrIdTour()[i]);
                    HoaDon dataInsert = new HoaDon();
                    setUpDataInsert(dataInsert, request);

                    TourEntity detailTour = tourReponsitory.getById(request.getArrIdTour()[i]);
                    if (detailTour == null){
                        return new ResponseCRUD("truyền sai id Tour");
                    }else {
                        dataInsert.setTongSoTien(detailTour.getPrice());
                        dataInsert.setIdTour(detailTour.getId());
//                        dataInsert.setTongSoTien(500000);
//                        dataInsert.setIdTour(request.getArrIdTour()[i]);
                        dataInsert.setStatus(2);
                        thanhToanRepository.save(dataInsert);
                    System.out.println(" đã mua tour"+request.getArrIdTour()[i] );
                    }
                }
                atmService.updateAtmKhiThanhToan(searchAtmRequest, request.getTongSoTien());
                System.out.println("Thanh toán thành công");
                result.put("status", "SUCCESS");
                result.put("message", "Thanh toán thành công");
                return new ResponseCRUD(result);
            }
            System.out.println(active);

            return new ResponseCRUD("Xảy ra 1 lỗi case nào đó");
        }catch (Exception e){
            result.put("status", "ERROR");
            result.put("message", e.getMessage());
            return new ResponseCRUD(result);
        }

    }
//
//    @Override
//    public ResponseCRUD delete(Integer id) {
//        thanhToanRepository.deleteById(id);
//        return new ResponseCRUD("DELETE SUCCESS");
//    }
//
//    @Override
//    public ResponseCRUD update(Integer id, ThanhToanRequest request) {
//        HoaDon entity = new HoaDon();
//        convertRequestToEntity(request, entity, id);
//        HoaDon dataAdd = thanhToanRepository.save(entity);
//        return new ResponseCRUD(dataAdd);
//    }
//
//    private void convertRequestToEntity(ThanhToanRequest request, HoaDon hoaDon){
//        hoaDon.setTen(request.getTen());
//        hoaDon.setMota(request.getMota());
//    }
//    private void convertRequestToEntity(ThanhToanRequest request, HoaDon hoaDon, Integer id){
//        hoaDon.setTen(request.getTen());
//        hoaDon.setMota(request.getMota());
//        hoaDon.setId(id);
//    }

    private void setUpDataInsert(HoaDon hoaDon,ThanhToanRequest request ){
        // set thông tin người mua
        hoaDon.setIdNguoiMua(request.getIdNguoiMua());
        hoaDon.setTenNguoiDk(request.getTenNguoiDk());
        hoaDon.setSdtNguoiDk(request.getSdtNguoiDk());
        hoaDon.setSoCMNDNguoiDk(request.getSoCMNDNguoiDk());

        // set địa điểm đưa đón
        hoaDon.setDiaDiemDuaDon(request.getDiaDiemDuaDon());


    }
}
