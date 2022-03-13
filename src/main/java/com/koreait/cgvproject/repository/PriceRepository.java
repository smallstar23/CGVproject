package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Price;
import com.koreait.cgvproject.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findAllByTheaterOrderByWeekDescStartTimeAsc(Theater theater);
    Optional<Price> findFirstByTheater(Theater theater);

    void deleteAllByTheater(Theater theater);
}
