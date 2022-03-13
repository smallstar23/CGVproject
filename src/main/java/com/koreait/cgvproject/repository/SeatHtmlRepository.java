package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.model.entity.Hall;
import com.koreait.cgvproject.model.entity.Seathtml;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatHtmlRepository extends JpaRepository<Seathtml, Long> {
    Optional<Seathtml> findByHall(Hall hall);
}
