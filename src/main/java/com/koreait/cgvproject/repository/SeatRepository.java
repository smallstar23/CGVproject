package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.model.entity.Hall;
import com.koreait.cgvproject.model.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findByHallAndStNum(Hall hall, String stNum);
    List<Seat> findAllByHallOrderByStNum(Hall hall);
    void deleteAllByHall(Hall hall);
}
