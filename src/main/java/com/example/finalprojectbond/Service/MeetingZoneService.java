package com.example.finalprojectbond.Service;

import com.example.finalprojectbond.Api.ApiException;
import com.example.finalprojectbond.InDTO.MeetingZoneInDTO;
import com.example.finalprojectbond.Model.Experience;
import com.example.finalprojectbond.Model.MeetingZone;
import com.example.finalprojectbond.Model.Organizer;
import com.example.finalprojectbond.OutDTO.MeetingZoneOutDTO;
import com.example.finalprojectbond.Repository.ExperienceRepository;
import com.example.finalprojectbond.Repository.MeetingZoneRepository;
import com.example.finalprojectbond.Repository.OrganizerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingZoneService {
    private final MeetingZoneRepository meetingZoneRepository;
    private final OrganizerRepository organizerRepository;
    private final ExperienceRepository experienceRepository;

    public MeetingZoneOutDTO getMeetingZone(Integer organizerId,Integer experienceId){
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        MeetingZone meetingZone = meetingZoneRepository.findMeetingZoneByExperienceId(experienceId);
        if (organizer == null) {
            throw new ApiException("Organizer not found");
        }
        if (meetingZone == null) {
            throw new ApiException("Meeting Zone not found");
        }
        MeetingZoneOutDTO meetingZoneOutDTO = new MeetingZoneOutDTO(meetingZone.getLatitude(),meetingZone.getLongitude(),meetingZone.getLandMark());
        return meetingZoneOutDTO;
    }

    public void createMeetingZone(Integer organizerId,Integer experienceId,MeetingZoneInDTO meetingZoneInDTO) {
        Experience experience = experienceRepository.findExperienceById(experienceId);
        if(experience==null) {
            throw new ApiException("Experience not found");
        }
        MeetingZone meetingZone = new MeetingZone(null,meetingZoneInDTO.getLatitude(),meetingZoneInDTO.getLongitude(),meetingZoneInDTO.getLandMark(),experience);
        Organizer organizer =organizerRepository.findOrganizerById(organizerId);
        if(organizer==null) {
            throw new ApiException("Organizer not found");
        }
        if (experience.getOrganizer().getId() == organizer.getId()) {
            meetingZoneRepository.save(meetingZone);
        }else {
            throw new ApiException("Organizer does not have this experience");
        }
    }

    public void updateMeetingZone(Integer organizerId,Integer experienceId,MeetingZoneInDTO meetingZoneInDTO){
        Organizer organizer =organizerRepository.findOrganizerById(organizerId);
        if(organizer==null) {
            throw new ApiException("Organizer not found");
        }
        Experience experience = experienceRepository.findExperienceById(experienceId);
        if(experience==null) {
            throw new ApiException("Experience not found");
        }
        MeetingZone meetingZone1 = meetingZoneRepository.findMeetingZoneById(experience.getMeetingZone().getId());
        if(meetingZone1==null) {
            throw new ApiException("MeetingZone not found");
        }
        if (experience.getOrganizer().getId() == organizer.getId()) {
            meetingZone1.setLatitude(meetingZoneInDTO.getLatitude());
            meetingZone1.setLongitude(meetingZoneInDTO.getLongitude());
            meetingZone1.setLandMark(meetingZoneInDTO.getLandMark());
            meetingZoneRepository.save(meetingZone1);
        }else {
            throw new ApiException("Organizer does not have this experience");
        }
    }

    public void deleteMeetingZone(Integer organizerId,Integer experienceId) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        Experience experience = experienceRepository.findExperienceById(experienceId);
        MeetingZone meetingZone = meetingZoneRepository.findMeetingZoneById(experience.getMeetingZone().getId());
        if(organizer==null) {
            throw new ApiException("Organizer not found");
        }
        if(experience==null) {
            throw new ApiException("Experience not found");
        }
        if(meetingZone==null) {
            throw new ApiException("MeetingZone not found");
        }
        if (experience.getOrganizer().getId() == organizer.getId()) {
            meetingZone.setExperience(null);
            meetingZoneRepository.delete(meetingZone);
        }else {
            throw new ApiException("Organizer does not have this experience");
        }
    }
}
