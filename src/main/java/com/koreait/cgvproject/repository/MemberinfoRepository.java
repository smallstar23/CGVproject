package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.MemberinfoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberinfoRepository extends CrudRepository<MemberinfoEntity, Long> {
    MemberinfoEntity findByUserid(String userid);
}
