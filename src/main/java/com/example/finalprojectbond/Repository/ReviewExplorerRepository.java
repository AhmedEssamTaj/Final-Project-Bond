package com.example.finalprojectbond.Repository;

import com.example.finalprojectbond.Model.Experience;
import com.example.finalprojectbond.Model.Explorer;
import com.example.finalprojectbond.Model.Organizer;
import com.example.finalprojectbond.Model.ReviewExplorer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewExplorerRepository extends JpaRepository<ReviewExplorer, Integer> {

    ReviewExplorer findReviewExplorerById(Integer id);

    List<ReviewExplorer> findByExplorer(Explorer explorer);

    List<ReviewExplorer> findByOrganizer(Organizer organizer);



    List<ReviewExplorer> findByOrganizerOrderByRatingDesc(Organizer organizer);

    List<ReviewExplorer> findByOrganizerOrderByRatingAsc(Organizer organizer);

}
