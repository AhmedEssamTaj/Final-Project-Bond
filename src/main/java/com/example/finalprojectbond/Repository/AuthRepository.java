package com.example.finalprojectbond.Repository;

import com.example.finalprojectbond.Model.MyUser;
import com.example.finalprojectbond.Model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthRepository extends JpaRepository<MyUser, Integer> {
    MyUser findMyUserByUsername(String username);
    MyUser findMyUserById(Integer id);

    List<MyUser> findAllByRoleOrderByRatingDesc(String role);
    List<MyUser> findAllByRoleOrderByRatingAsc(String role);

    @Query("select u from MyUser u where u.city=?1 and u.role=?2")
    List<MyUser> findAllByCityAndRole(String city, String role);
}
