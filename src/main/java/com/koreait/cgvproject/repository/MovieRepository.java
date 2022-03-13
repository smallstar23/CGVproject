package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findByMcode(Long mcode);
}
