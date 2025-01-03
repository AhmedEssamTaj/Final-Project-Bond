package com.example.finalprojectbond.Repository;

import com.example.finalprojectbond.Model.ExperiencePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperiencePhotoRepository extends JpaRepository<ExperiencePhoto, Integer> {

    ExperiencePhoto findExperiencePhotoById(Integer id);
    List<ExperiencePhoto> findByExperienceId(Integer experienceId);
    List<ExperiencePhoto>findExperiencePhotoByExperienceId(Integer experienceId);
}
