package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Director;
import com.koreait.cgvproject.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiretorRepository extends JpaRepository<Director, Long> {

    Director findByMovie(Movie movie);

}
