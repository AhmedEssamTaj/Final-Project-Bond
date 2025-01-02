package com.example.finalprojectbond.Repository;

import com.example.finalprojectbond.Model.Explorer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExplorerRepository extends JpaRepository<Explorer, Integer> {
}
