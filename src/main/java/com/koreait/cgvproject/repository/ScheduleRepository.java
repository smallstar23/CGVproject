package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    List<Schedule> findAllByMovieAndHall(Movie movie, Hall hall);
<<<<<<< HEAD
    List<Schedule> findAllByMovieOrderByScdate(Movie movie);
=======

    List<Schedule> findAllByHallAndScdateBetween(Hall hall, LocalDateTime startdate, LocalDateTime enddate);

>>>>>>> b626a69cbc894c2347f562cd76321fb7f315b013
}
