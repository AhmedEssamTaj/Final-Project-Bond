package com.example.finalprojectbond.Repository;

import com.example.finalprojectbond.Model.ReviewExplorer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewExplorerRepository extends JpaRepository<ReviewExplorer, Integer> {
}
