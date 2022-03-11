package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository <Actor ,Long> {
}
