package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Movie;
import com.koreait.cgvproject.entity.Trailer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrailerRepository extends JpaRepository<Trailer, Long> {


    Trailer findByMovie(Movie movie);

    
}
