package com.example.finalprojectbond.Repository;

import com.example.finalprojectbond.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    Notification findNotificationById(Integer id);

}
