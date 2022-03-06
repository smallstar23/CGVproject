package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.AdminNewsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface AdminNewsRepository extends CrudRepository<AdminNewsEntity, Long> {
    @Override
    ArrayList<AdminNewsEntity> findAll();
}
