package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
//    @Override
//    ArrayList<Notification> findAll();
}
