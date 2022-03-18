package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.GiftPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface GiftPaymentRepository extends JpaRepository<GiftPayment, Long> {


    // 특정 멤버아이디값의 모든 상품결제내역 조회
    @Query(value = "SELECT * FROM gift_payment where mem_idx = :memberIdx", nativeQuery = true)
    List<GiftPayment> findByMemberIdx(Long memberIdx);

//    GiftPayment findByUserid(Long memberIdx);

//    @Query(value = "select p.gpcode, m.username, g.title, g.price, p.regDate, p.status from  GiftPayment p, p.gift g, p.member m where p.member = m.idx and g.gcode= p.gift and p.member = :memberIdx")
//    List<GiftPaymentListDTO> findListAll(Long memberIdx);
}