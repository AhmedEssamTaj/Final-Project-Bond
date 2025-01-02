package com.example.finalprojectbond.Repository;

import com.example.finalprojectbond.Model.ExperiencePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperiencePhotoRepository extends JpaRepository<ExperiencePhoto, Integer> {
}
