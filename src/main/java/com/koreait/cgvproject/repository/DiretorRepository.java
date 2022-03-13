package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.model.entity.Director;
import com.koreait.cgvproject.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiretorRepository extends JpaRepository<Director, Long> {

    Director findByMovie(Movie movie);

}
