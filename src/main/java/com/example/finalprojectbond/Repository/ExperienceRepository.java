package com.example.finalprojectbond.Repository;

import com.example.finalprojectbond.Model.Application;
import com.example.finalprojectbond.Model.Experience;
import com.example.finalprojectbond.Model.Explorer;
import com.example.finalprojectbond.Model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Integer> {
    Experience findExperienceById(Integer id);

    List<Experience> findAllByOrganizer(Organizer organizer);

    List<Experience> findAllByTitleContainingIgnoreCaseAndOrganizer(String title, Organizer organizer);

    @Query("SELECT COUNT(e) > 0 FROM Experience exp JOIN exp.explorers e WHERE exp.id = :experienceId AND e.id = :explorerId")
    boolean existsByExperienceIdAndExplorerId(@Param("experienceId") Integer experienceId, @Param("explorerId") Integer explorerId);

    @Query("SELECT e.explorers FROM Experience e WHERE e.id = :experienceId")
    List<Explorer> findExplorersByExperienceId(@Param("experienceId") Integer experienceId);

    @Query("SELECT a.explorer FROM Experience e JOIN e.applications a WHERE e.id = :experienceId AND a.status = 'Accepted'")
    List<Explorer> findAcceptedExplorersByExperienceId(@Param("experienceId") Integer experienceId);
}
