package com.example.finalprojectbond.Repository;

import com.example.finalprojectbond.Model.Explorer;
import com.example.finalprojectbond.Model.ReviewExplorer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewExplorerRepository extends JpaRepository<ReviewExplorer, Integer> {

    List<ReviewExplorer> findAllByExplorer(Explorer explorer);

}
