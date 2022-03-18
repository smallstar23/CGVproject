package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Member;
import com.koreait.cgvproject.entity.Point;
import com.koreait.cgvproject.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

    List<Point> findAllByMemberAndTheater(Member member, Theater theater);
}