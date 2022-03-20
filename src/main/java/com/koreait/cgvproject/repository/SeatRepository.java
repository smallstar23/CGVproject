package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findByHallAndStNum(Hall hall, String stNum);
    List<Seat> findAllByHallOrderByStNum(Hall hall);
    Long countAllByHallAndDisabledEquals(Hall hall, Integer disabled);
    void deleteAllByHall(Hall hall);
}
