package com.example.finalprojectbond.Repository;

import com.example.finalprojectbond.Model.Application;
import com.example.finalprojectbond.Model.Experience;
import com.example.finalprojectbond.Model.Explorer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;
import java.util.List;

@Repository
public interface ExplorerRepository extends JpaRepository<Explorer, Integer> {

    Explorer findExplorerById(Integer id);
     List<Explorer> findAllByApplications(Set<Application> applications);

}
