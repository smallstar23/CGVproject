package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Hall;
import com.koreait.cgvproject.entity.Member;
import com.koreait.cgvproject.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUserid(String userid);
}
