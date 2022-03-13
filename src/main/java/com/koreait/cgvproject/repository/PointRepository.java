package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

    // 특정 멤버아이디값의 포인트내역 조회
    @Query(value = "SELECT * FROM point where mem_idx = :memberIdx AND tcode= :tcode", nativeQuery = true)
    List<Point> findByMemberIdx(Long memberIdx, Long tcode);

}