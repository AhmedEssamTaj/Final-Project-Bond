package com.example.finalprojectbond.Service;

import com.example.finalprojectbond.Api.ApiException;
import com.example.finalprojectbond.Model.MyUser;
import com.example.finalprojectbond.Model.Organizer;
import com.example.finalprojectbond.Repository.AuthRepository;
import com.example.finalprojectbond.Repository.OrganizerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final OrganizerRepository organizerRepository;

    public void approveOrganizer(Integer adminId,Integer organizerId){
        MyUser user = authRepository.findMyUserById(adminId);
        if(user == null){
            throw new ApiException("admin not found");
        }
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if(organizer == null){
            throw new ApiException("organizer not found");
        }
        organizer.setIsApproved(true);
    }
}
