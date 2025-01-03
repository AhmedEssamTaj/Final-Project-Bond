package com.example.finalprojectbond.Repository;

import com.example.finalprojectbond.Model.MeetingZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingZoneRepository extends JpaRepository<MeetingZone, Integer> {
    MeetingZone findMeetingZoneById(Integer id);
    MeetingZone findMeetingZoneByExperienceId(Integer experienceId);
}
