package com.koreait.cgvproject.repository;

import com.koreait.cgvproject.entity.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
    @Override
    ArrayList<Notification> findAll();
}
