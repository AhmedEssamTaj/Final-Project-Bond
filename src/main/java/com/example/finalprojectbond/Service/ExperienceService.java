package com.example.finalprojectbond.Service;

import com.example.finalprojectbond.Api.ApiException;
import com.example.finalprojectbond.InDTO.ExperienceInDTO;
import com.example.finalprojectbond.Model.*;
import com.example.finalprojectbond.OutDTO.BriefExplorerOutDTO;
import com.example.finalprojectbond.OutDTO.ExperienceOutDTO;
import com.example.finalprojectbond.OutDTO.ExperiencePhotoOutDTO;
import com.example.finalprojectbond.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ExperiencePhotoRepository experiencePhotoRepository;
    private final OrganizerRepository organizerRepository;
    private final MeetingZoneRepository meetingZoneRepository;
    private final ExplorerRepository explorerRepository;
    private final ExplorerService explorerService;
    private final NotificationRepository notificationRepository;

    // method to get all Experiences
    public List<ExperienceOutDTO> findAll() {
        return convertToOutDTO(experienceRepository.findAll());
    }

    // Create Experience
    public void createExperience(ExperienceInDTO experienceInDTO) {
        Organizer organizer = organizerRepository.findOrganizerById(experienceInDTO.getOrganizerId());
        if (organizer == null) {
            throw new ApiException("Organizer not found");
        }
        Experience experience = new Experience();
        experience.setTitle(experienceInDTO.getTitle());
        experience.setDescription(experienceInDTO.getDescription());
        experience.setCity(experienceInDTO.getCity());
        experience.setDifficulty(experienceInDTO.getDifficulty());
        experience.setStartDate(experienceInDTO.getStartDate());
        experience.setEndDate(experienceInDTO.getEndDate());
        experience.setAudienceType(experienceInDTO.getAudienceType());
        experience.setOrganizer(organizer);
        experienceRepository.save(experience);

    }

    // update experience
    public void updateExperience(Integer experienceId, ExperienceInDTO experienceInDTO) {
        Experience experience = experienceRepository.findExperienceById(experienceId);
        if (experience == null) {
            throw new ApiException("Experience not found");
        }
        experience.setTitle(experienceInDTO.getTitle());
        experience.setDescription(experienceInDTO.getDescription());
        experience.setCity(experienceInDTO.getCity());
        experience.setDifficulty(experienceInDTO.getDifficulty());
        experience.setStartDate(experienceInDTO.getStartDate());
        experience.setEndDate(experienceInDTO.getEndDate());
        experience.setAudienceType(experienceInDTO.getAudienceType());
        experienceRepository.save(experience);
    }

    // Delete Experience
    public void deleteExperience(Integer experienceId) {
        MeetingZone meetingZone = meetingZoneRepository.findMeetingZoneById(experienceId);
        Experience experience = experienceRepository.findExperienceById(experienceId);
        if (experience == null) {
            throw new ApiException("Experience not found");
        }

        if (meetingZone != null) {
            experience.setMeetingZone(null);
            meetingZone.setExperience(null);
        }
        experienceRepository.delete(experience);
    }

    public List<ExperienceOutDTO> convertToOutDTO(List<Experience> experienceList) {
        List<ExperienceOutDTO> experienceOutDTOList = new ArrayList<>();
        for (Experience experience : experienceList) {
            experienceOutDTOList.add(new ExperienceOutDTO(experience.getTitle(),
                    experience.getDescription(),
                    experience.getCity(),
                    experience.getStatus(),
                    experience.getStartDate(),
                    experience.getEndDate(),
                    experience.getDifficulty(),
                    experience.getAudienceType(),
                    convertPhotosToDTO(experiencePhotoRepository.findByExperienceId(experience.getId()))
            ));
        }
        return experienceOutDTOList;
    }

    public List<ExperiencePhotoOutDTO> convertPhotosToDTO(List<ExperiencePhoto> experiencePhotoList) {
        List<ExperiencePhotoOutDTO> experiencePhotoOutDTOS = new ArrayList<>();
        for (ExperiencePhoto experiencePhoto : experiencePhotoList) {
            experiencePhotoOutDTOS.add(new ExperiencePhotoOutDTO(experiencePhoto.getPhotoUrl()));
        }
        return experiencePhotoOutDTOS;
    }

    // method to change the status of experience to fully booked
    public void changeStatusToFullyBooked(Integer organizerId, Integer experienceId) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null) {
            throw new ApiException("Organizer not found");
        }
        Experience experience = experienceRepository.findExperienceById(experienceId);
        if (experience == null) {
            throw new ApiException("Experience not found");
        }
        if (!experience.getOrganizer().equals(organizer)) {
            throw new ApiException("Organizer not allowed to change status");
        }
        if (!experience.getStatus().equals("Accept Application")){
            throw new ApiException("Experience not allowed to change status");
        }
        experience.setStatus("Fully Booked");
        experienceRepository.save(experience);
    }

    // Method to remove explorer from experience
    public void RemoveExplorerFromExperience(Integer organizerId, Integer explorerId, Integer experienceId) {
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
        if (!experience.getOrganizer().equals(organizer)) {
            throw new ApiException("Organizer not allowed to remove explorer because this experience is not his");
        }

        if (!experienceRepository.existsByExperienceIdAndExplorerId(experienceId, explorerId)) {
            throw new ApiException("Explorer is not a part of this experience");
        }

        List<Explorer> explorers = experienceRepository.findExplorersByExperienceId(experienceId);

        Set<Explorer> updatedExplorers = explorers.stream()
                .filter(explorerEx -> !explorer.getId().equals(explorerId))
                .collect(Collectors.toSet()); // Converted to a Set here


        experience.setExplorers(updatedExplorers);
        experienceRepository.save(experience);
    }


    // method to remove All explorers who did not confirm the meeting zone
    public void removeAllNotConfirmedExplorersFromExperience(Integer organizerId, Integer experienceId) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null) {
            throw new ApiException("Organizer not found");
        }
        Experience experience = experienceRepository.findExperienceById(experienceId);
        if (experience == null) {
            throw new ApiException("Experience not found");
        }
        if (!experience.getOrganizer().equals(organizer)) {
            throw new ApiException("Organizer not allowed to remove anyone because this experience is not his");
        }

        List<Explorer> explorers = experienceRepository.findExplorersByExperienceId(experienceId);

        Set<Explorer> confirmedExplorers = explorers.stream()
                .filter(Explorer::getMeetingZone)
                .collect(Collectors.toSet());
        //
        experience.setExplorers(confirmedExplorers);
        experienceRepository.save(experience);
    }

    // method to get all explorers of an experience that did not confirm the meeting zone
    public List<BriefExplorerOutDTO> getALlNonConfirmedExplorers (Integer organizerId, Integer experienceId) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null) {
            throw new ApiException("Organizer not found");
        }
        Experience experience = experienceRepository.findExperienceById(experienceId);
        if (experience == null) {
            throw new ApiException("Experience not found");
        }
        if (!experience.getOrganizer().equals(organizer)) {
            throw new ApiException("Organizer not allowed to remove anyone because this experience is not his");
        }
        if (!experience.getStatus().equals("Confirming")){
            throw new ApiException("Experience not allowed to change status");
        }
        List<Explorer> explorers = experienceRepository.findExplorersByExperienceId(experienceId);
        List<Explorer> confirmedExplorers = new ArrayList<>();
        for (Explorer explorer : explorers) {
            if (!explorer.getMeetingZone()) {
               confirmedExplorers.add(explorer);
            }
        }
        return explorerService.getBriefExplorer(confirmedExplorers);
    }

    // method to get All accepted explorers
    public List<BriefExplorerOutDTO> getAllAcceptedExplorers (Integer organizerId, Integer experienceId) {
        // ========================================================
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null) {
            throw new ApiException("Organizer not found");
        }
        Experience experience = experienceRepository.findExperienceById(experienceId);
        if (experience == null) {
            throw new ApiException("Experience not found");
        }
        if (!experience.getOrganizer().equals(organizer)) {
            throw new ApiException("Organizer not allowed to get accepted explorers because this experience is not his");
        }

        // ===========================================================================


        return explorerService.getBriefExplorer(experienceRepository.findAcceptedExplorersByExperienceId(experienceId));
    }

    // method to make the experience complete
    public void changeStatusToCompleted (Integer organizerId, Integer experienceId) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null) {
            throw new ApiException("Organizer not found");
        }
        Experience experience = experienceRepository.findExperienceById(experienceId);
        if (experience == null) {
            throw new ApiException("Experience not found");
        }
        if (!experience.getOrganizer().equals(organizer)) {
            throw new ApiException("this Organizer is not allowed to change status of the experience");
        }
        if (!experience.getStatus().equals("Active")){
            throw new ApiException("Experience not allowed to change status");
        }
        experience.setStatus("Completed");
        experienceRepository.save(experience);
    }

    // method to change the status of the experience to canceled and create notification
    public void cancelExperience(Integer organizerId, Integer experienceId) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null) {
            throw new ApiException("Organizer not found");
        }
        Experience experience = experienceRepository.findExperienceById(experienceId);
        if (experience == null) {
            throw new ApiException("Experience not found");
        }
        if (!experience.getOrganizer().equals(organizer)) {
            throw new ApiException("this Organizer is not allowed to cancel experience");
        }
        if (experience.getStatus().equals("Completed")){
            throw new ApiException("Experience is already completed");
        }
        experience.setStatus("Canceled");
        experienceRepository.save(experience);

        List<Explorer> explorers = experienceRepository.findExplorersByExperienceId(experienceId);
        for (Explorer explorer : explorers) {
            Notification notification = new Notification();
            notification.setExperience(experience);
            notification.setExplorer(explorer);
            notification.setMessage("Hello "+ explorer.getMyUser().getName()+" ,  \n" +
                    "The experience { "+experience.getTitle()+" } has been canceled. We apologize for the inconvenience.");

            notificationRepository.save(notification);
        }
    }

}
