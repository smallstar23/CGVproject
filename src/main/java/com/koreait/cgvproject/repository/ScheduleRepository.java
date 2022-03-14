package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByMovieAndHall(Movie movie, Hall hall);
}
