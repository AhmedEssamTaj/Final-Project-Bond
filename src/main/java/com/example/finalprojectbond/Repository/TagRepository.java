package com.example.finalprojectbond.Repository;

import com.example.finalprojectbond.Model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Tag findTagById(Intger Id);
}
