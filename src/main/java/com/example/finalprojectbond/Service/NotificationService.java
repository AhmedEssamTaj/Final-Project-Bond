package com.example.finalprojectbond.Service;

import com.example.finalprojectbond.Api.ApiException;
import com.example.finalprojectbond.InDTO.NotificationInDTO;
import com.example.finalprojectbond.Model.*;
import com.example.finalprojectbond.OutDTO.NotificationOutDTO;
import com.example.finalprojectbond.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final OrganizerRepository organizerRepository;
    private final ExplorerRepository explorerRepository;
    private final ExperienceRepository experienceRepository;
    private final ApplicationRepository applicationRepository;

    public void notificationOneExplorer(Integer organizerId,Integer explorerId,Integer experienceId) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null) {
            throw new ApiException("Organizer not found");
        }
        Explorer explorer = explorerRepository.findExplorerById(explorerId);
        if (explorer == null) {
            throw new ApiException("Explorer not found");
        }
        Experience experience = experienceRepository.findExperienceById(experienceId);
        if (experience == null) {
            throw new ApiException("Experience not found");
        }
        Application application = applicationRepository.findApplicationByExperienceAndExplorer(experience, explorer);
        if (application == null) {
            throw new ApiException("Application not found");
        }
        if (!experience.getOrganizer().equals(organizer)) {
            throw new ApiException("Organizer does not have this experience");
        }
        if (!experience.getExplorers().equals(explorer)) {
            throw new ApiException("Explorers does not have this experience");
        }

        if (!experience.getStatus().equalsIgnoreCase("Confirming")) {
            throw new ApiException("the status of experience should be Confirming");
        }

        if (!application.getStatus().equalsIgnoreCase("Accepted")) {
            throw new ApiException("the status of application should be Accepted");
        }

        //if (!application.getMeetingZone == false){
        //    throw new ApiException("the meeting zone is Confirmed");
        //}

        Notification notification = new Notification("Action Needed: Confirm Your Meeting Zone","Organizer of: "+experience.getTitle());
        explorer.getNotifications().add(notification);
        notificationRepository.save(notification);
    }

    public List<NotificationOutDTO> getMyNotifications(Integer explorerId) {
        Explorer explorer = explorerRepository.findExplorerById(explorerId);
        if (explorer == null) {
            throw new ApiException("Explorer not found");
        }
        List<Notification> notifications = notificationRepository.findAllByExplorer(explorer);
        return changeNotificationToOutDTO(notifications);
    }

    public List<NotificationOutDTO> changeNotificationToOutDTO(List<Notification> notifications){
        List<NotificationOutDTO> notificationOutDTOs = new ArrayList<>();
        for (Notification notification : notifications) {
            notificationOutDTOs.add(new NotificationOutDTO(notification.getMessage(),notification.getTitle(),notification.getNotification_createAt(),notification.getExperience().getTitle()));
        }
        return notificationOutDTOs;
    }

}
