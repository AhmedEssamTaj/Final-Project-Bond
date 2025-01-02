package com.example.finalprojectbond.Service;

import com.example.finalprojectbond.Api.ApiException;
import com.example.finalprojectbond.InDTO.ApplicationInDTO;
import com.example.finalprojectbond.Model.Application;
import com.example.finalprojectbond.Model.Experience;
import com.example.finalprojectbond.Model.Explorer;
import com.example.finalprojectbond.Repository.ApplicationRepository;
import com.example.finalprojectbond.Repository.ExperienceRepository;
import com.example.finalprojectbond.Repository.ExplorerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ExplorerRepository explorerRepository;
    private final ExperienceRepository experienceRepository;

    public List<Application> getMyApplications(Integer explorerId) {
        Explorer explorer = explorerRepository.findExplorerById(explorerId);
        if (explorer == null) {
            throw new ApiException("Explorer not found");
        }
        if (explorer.getApplications() == null) {
            throw new ApiException("Explorer does not have any Applications ");
        }
        List<Application> applications = new ArrayList<>();
        for (Application application : explorer.getApplications()) {
            applications.add(application);
        }
        return applications;
    }

    public void createApplication(Integer explorerId,Integer experienceId,ApplicationInDTO applicationInDTO) {
        Explorer explorer = explorerRepository.findExplorerById(explorerId);
        if (explorer == null) {
            throw new ApiException("Explorer not found");
        }
        Experience experience = experienceRepository.findExperienceById(experienceId);
        if (experience == null) {
            throw new ApiException("Experience not found");
        }
        Application application = new Application();
        application.setDescription(applicationInDTO.getDescription());
        application.setTools(applicationInDTO.getTools());
        application.setExperience(experience);
        application.setExplorer(explorer);
        applicationRepository.save(application);
    }

    public void updateApplication(Integer explorerId,Integer experienceId,Integer applicationId,ApplicationInDTO applicationInDTO) {
        Explorer explorer = explorerRepository.findExplorerById(explorerId);
        if (explorer == null) {
            throw new ApiException("Explorer not found");
        }
        Experience experience = experienceRepository.findExperienceById(experienceId);
        if (experience == null) {
            throw new ApiException("Experience not found");
        }
        Application application = applicationRepository.findApplicationById(applicationId);
        if (application == null) {
            throw new ApiException("Application not found");
        }
        if (experience.getApplications().contains(application) && explorer.getApplications().contains(application)) {
            application.setDescription(applicationInDTO.getDescription());
            application.setTools(applicationInDTO.getTools());
            applicationRepository.save(application);
        }else {
            throw new ApiException("Explorer does not have this Application");
        }
    }

    public void deleteApplication(Integer explorerId,Integer experienceId,Integer applicationId) {
        Explorer explorer = explorerRepository.findExplorerById(explorerId);
        if (explorer == null) {
            throw new ApiException("Explorer not found");
        }
        Experience experience = experienceRepository.findExperienceById(experienceId);
        if (experience == null) {
            throw new ApiException("Experience not found");
        }
        Application application = applicationRepository.findApplicationById(applicationId);
        if (application == null) {
            throw new ApiException("Application not found");
        }
        if (experience.getApplications().contains(application) && explorer.getApplications().contains(application)) {
            applicationRepository.delete(application);
        }else {
            throw new ApiException("Explorer does not have this Application");
        }
    }
}
