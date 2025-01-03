package com.example.finalprojectbond.Repository;

import com.example.finalprojectbond.Model.Application;
import com.example.finalprojectbond.Model.Experience;
import com.example.finalprojectbond.Model.Explorer;
import com.example.finalprojectbond.Model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Integer> {
    Experience findExperienceById(Integer id);

    List<Experience> findAllByOrganizer(Organizer organizer);

    List<Experience> findAllByTitleContainingIgnoreCaseAndOrganizer(String title, Organizer organizer);

}
