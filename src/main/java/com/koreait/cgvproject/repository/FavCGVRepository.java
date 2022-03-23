package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.FavCGV;
import com.koreait.cgvproject.entity.GiftPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavCGVRepository extends JpaRepository<FavCGV, Long> {
    // 특정 멤버아이디값의 모든 자주가는 CGV 조회
    @Query(value = "SELECT * FROM favCGV where mem_idx = :memberIdx", nativeQuery = true)
    List<FavCGV> findByMemberIdx(Long memberIdx);
}
