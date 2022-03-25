package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.FavCGV;
import com.koreait.cgvproject.entity.GiftPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface FavCGVRepository extends JpaRepository<FavCGV, Long> {
    // 특정 멤버아이디값의 모든 자주가는 CGV 조회
    List<FavCGV> findAllByMemIdx(Long memberIdx);

    FavCGV findByMemIdxAndTname(Long memberIdx, String tname);
    void deleteByIdx(Long idx);


}
