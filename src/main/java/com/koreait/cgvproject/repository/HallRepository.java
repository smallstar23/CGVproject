package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.entity.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

    List<Hall> findAllByTheater(Theater theater);
}
