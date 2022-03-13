package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.model.entity.Hall;
import com.koreait.cgvproject.model.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    List<Hall> findAllByTheaterOrderByHcode(Theater theater);

    List<Hall> findAllByTheater(Theater theater);

    Hall findByHcode(Long hcode);

}
