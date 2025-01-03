package com.example.finalprojectbond.Service;

import com.example.finalprojectbond.Api.ApiException;
import com.example.finalprojectbond.InDTO.ExperienceInDTO;
import com.example.finalprojectbond.InDTO.OrganizerInDTO;
import com.example.finalprojectbond.Model.Experience;
import com.example.finalprojectbond.Model.MyUser;
import com.example.finalprojectbond.Model.Organizer;
import com.example.finalprojectbond.OutDTO.ExperienceOutDTO;
import com.example.finalprojectbond.OutDTO.ExperienceSearchOutDTO;
import com.example.finalprojectbond.OutDTO.OrganizerFilterOutDTO;
import com.example.finalprojectbond.OutDTO.OrganizerOutDTO;
import com.example.finalprojectbond.Repository.AuthRepository;
import com.example.finalprojectbond.Repository.ExperienceRepository;
import com.example.finalprojectbond.Repository.OrganizerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerService {
    private final OrganizerRepository organizerRepository;
    private final AuthRepository authRepository;
    private final ExperienceRepository experienceRepository;


    public OrganizerOutDTO getMyOrganizer(Integer organizerId){
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        MyUser user = authRepository.findMyUserById(organizer.getId());
        if (organizer == null || user == null) {
            throw new ApiException("Organizer not found");
        }
        OrganizerOutDTO organizerOutDTO = new OrganizerOutDTO(user.getUsername(),user.getName(), user.getAge(), user.getCity(),user.getHealthStatus(),user.getEmail(),user.getGender(),user.getRole(),user.getPhoneNumber(),user.getPhotoURL(),organizer.getUserProfileSummary(),organizer.getNumberOfExperience(),organizer.getLicenseSerialNumber(),user.getRating(),organizer.getIsApproved());
        return organizerOutDTO;
    }


    public void registerOrganizer(OrganizerInDTO organizerInDTO) {
        //String hashPassword = new BCryptPasswordEncoder().encode(organizerInDTO.getPassword());
        MyUser myUser = new MyUser(organizerInDTO.getUsername(),organizerInDTO.getPassword(),organizerInDTO.getName(),organizerInDTO.getAge(),organizerInDTO.getCity(), organizerInDTO.getHealthStatus(),organizerInDTO.getEmail(),organizerInDTO.getGender(),"ORGANIZER",organizerInDTO.getPhoneNumber(),organizerInDTO.getPhotoURL());
        Organizer organizer = new Organizer(organizerInDTO.getUserProfileSummary(),organizerInDTO.getLicenseSerialNumber());
        organizerRepository.save(organizer);
        authRepository.save(myUser);
    }

    public void updateOrganizer(Integer organizer_id,OrganizerInDTO organizerInDTO) {
        MyUser myUser = authRepository.findMyUserById(organizer_id);
        Organizer organizer = organizerRepository.findOrganizerById(organizer_id);
        if (organizer == null || myUser == null) {
            throw new ApiException("Organizer not found");
        }
        myUser.setUsername(organizerInDTO.getUsername());
        myUser.setPassword(organizerInDTO.getPassword());
        myUser.setName(organizerInDTO.getName());
        myUser.setAge(organizerInDTO.getAge());
        myUser.setCity(organizerInDTO.getCity());
        myUser.setHealthStatus(organizerInDTO.getHealthStatus());
        myUser.setEmail(organizerInDTO.getEmail());
        myUser.setGender(organizerInDTO.getGender());
        myUser.setPhoneNumber(organizerInDTO.getPhoneNumber());
        organizerRepository.save(organizer);
    }

    public void deleteMyOrganizer(Integer organizer_id) {
        MyUser myUser = authRepository.findMyUserById(organizer_id);
        Organizer organizer = organizerRepository.findOrganizerById(myUser.getId());
        if (myUser == null || organizer == null) {
            throw new ApiException("Organizer not found");
        }
        myUser.setOrganizer(null);
        organizerRepository.delete(organizer);
        authRepository.delete(myUser);
    }

    public List<OrganizerFilterOutDTO> getOrganizerByRatingAsc(){
        List<MyUser> users = authRepository.findAllByRoleOrderByRatingAsc("ORGANIZER");
        if (users == null ) {
            throw new ApiException("Organizer not found");
        }
        List<OrganizerFilterOutDTO> organizerFilterOutDTO=new ArrayList<>();
        for (MyUser user : users) {
            organizerFilterOutDTO.add(new OrganizerFilterOutDTO(user.getPhotoURL(), user.getUsername(),user.getName(), user.getCity(),user.getOrganizer().getUserProfileSummary(), user.getRating()));
        }
        return organizerFilterOutDTO;
    }

    public List<OrganizerFilterOutDTO> getOrganizerByRatingDesc(){
        List<MyUser> users = authRepository.findAllByRoleOrderByRatingDesc("ORGANIZER");
        if (users == null ) {
            throw new ApiException("Organizer not found");
        }
        List<OrganizerFilterOutDTO> organizerFilterOutDTO=new ArrayList<>();
        for (MyUser user : users) {
            organizerFilterOutDTO.add(new OrganizerFilterOutDTO(user.getPhotoURL(), user.getUsername(),user.getName(), user.getCity(),user.getOrganizer().getUserProfileSummary(), user.getRating()));
        }
        return organizerFilterOutDTO;
    }

    public List<OrganizerFilterOutDTO> getOrganizerByCity(String city){
        List<MyUser> users = authRepository.findAllByCityAndRole(city,"ORGANIZER");
        if (users == null ) {
            throw new ApiException("Organizer not found");
        }
        List<OrganizerFilterOutDTO> organizerFilterOutDTO=new ArrayList<>();
        for (MyUser user : users) {
            organizerFilterOutDTO.add(new OrganizerFilterOutDTO(user.getPhotoURL(), user.getUsername(),user.getName(), user.getCity(),user.getOrganizer().getUserProfileSummary(), user.getRating()));
        }
        return organizerFilterOutDTO;
    }

    public List<ExperienceOutDTO> getAllOrganizerExperiences(Integer organizerId){
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null) {
            throw new ApiException("Organizer not found");
        }
        List<Experience> experiences = experienceRepository.findAllByOrganizer(organizer);
        return changeExperienceToOutDTO(experiences);
    }

    public List<ExperienceOutDTO> changeExperienceToOutDTO(List<Experience> experiences){
        List<ExperienceOutDTO> experienceOutDTOS = new ArrayList<>();
        for (Experience experience : experiences) {
            ExperienceOutDTO experienceOutDTO = new ExperienceOutDTO(experience.getTitle(),experience.getDescription(),experience.getCity(),experience.getStatus(),experience.getStartDate(),experience.getEndDate(),experience.getCreatedAt(),experience.getDifficulty());
            experienceOutDTOS.add(experienceOutDTO);
        }
        return experienceOutDTOS;
    }


    public List<ExperienceSearchOutDTO> searchExperienceByTitle(Integer organizerId,String title){
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null) {
            throw new ApiException("Organizer not found");
        }
        List<Experience> experiences1 = experienceRepository.findAllByTitleContainingIgnoreCaseAndOrganizer(title,organizer);
        return changeExperienceToSearchOutDTO(experiences1);
    }

    public List<ExperienceSearchOutDTO> changeExperienceToSearchOutDTO(List<Experience> experiences){
        List<ExperienceSearchOutDTO> experienceOutDTOS = new ArrayList<>();
        for (Experience experience : experiences) {
            ExperienceSearchOutDTO experienceOutDTO = new ExperienceSearchOutDTO(experience.getTitle(),experience.getStartDate(),experience.getEndDate(),experience.getDescription(),experience.getStatus());
            experienceOutDTOS.add(experienceOutDTO);
        }
        return experienceOutDTOS;
    }



}
