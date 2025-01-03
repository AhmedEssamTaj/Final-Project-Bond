package com.example.finalprojectbond.Service;

import com.example.finalprojectbond.Repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void notificationOneExplorer(Integer organizerId,Integer e) {

    }
}
