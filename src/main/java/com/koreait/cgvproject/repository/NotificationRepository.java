package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
//    @Override
//    ArrayList<Notification> findAll();
}
