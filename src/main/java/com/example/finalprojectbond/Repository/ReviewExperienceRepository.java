package com.example.finalprojectbond.Repository;

import com.example.finalprojectbond.Model.ReviewExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewExperienceRepository extends JpaRepository<ReviewExperience, Integer> {
}
