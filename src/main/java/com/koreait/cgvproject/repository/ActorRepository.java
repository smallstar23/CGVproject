package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.dto.ActorDTO;
import com.koreait.cgvproject.entity.Actor;
import com.koreait.cgvproject.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActorRepository extends JpaRepository <Actor ,Long> {

    Actor findByMovie(Movie movie);

    @Query(value = "SELECT * FROM Actor where mcode = :mcode", nativeQuery = true)
    List<Actor> findByMcode(Long mcode);
}
