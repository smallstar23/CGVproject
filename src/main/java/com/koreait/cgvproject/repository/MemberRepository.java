package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUserid(String userid);
}
