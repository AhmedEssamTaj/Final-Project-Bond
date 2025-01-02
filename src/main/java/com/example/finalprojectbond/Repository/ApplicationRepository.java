package com.example.finalprojectbond.Repository;

import com.example.finalprojectbond.Model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Application findApplicationById (Integer id);
}
