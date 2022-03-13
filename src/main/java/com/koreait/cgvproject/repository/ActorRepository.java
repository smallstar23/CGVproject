package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.model.entity.Actor;
import com.koreait.cgvproject.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository <Actor ,Long> {

    Actor findByMovie(Movie movie);

}
