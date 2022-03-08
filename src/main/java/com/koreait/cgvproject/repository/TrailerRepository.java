package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Theater;
import com.koreait.cgvproject.entity.Trailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrailerRepository extends JpaRepository<Trailer, Long> {
    Trailer findByMcode(Long mcode);

}
