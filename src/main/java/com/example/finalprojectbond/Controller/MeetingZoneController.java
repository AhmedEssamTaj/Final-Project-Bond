package com.example.finalprojectbond.Controller;

import com.example.finalprojectbond.Api.ApiResponse;
import com.example.finalprojectbond.InDTO.MeetingZoneInDTO;
import com.example.finalprojectbond.Model.MeetingZone;
import com.example.finalprojectbond.Service.MeetingZoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/meeting-zone")
@RequiredArgsConstructor
public class MeetingZoneController {
    private final MeetingZoneService meetingZoneService;

    @GetMapping("/{organizerId}/{experienceId}")
    public ResponseEntity getMeetingZone(@PathVariable Integer organizerId,@PathVariable Integer experienceId){
        return ResponseEntity.status(200).body(meetingZoneService.getMeetingZone(organizerId,experienceId));
    }

    @PostMapping("/create-meeting-zone/{organizerId}/{experienceId}")
    public ResponseEntity createMeetingZone(@PathVariable Integer organizerId,@PathVariable Integer experienceId,@RequestBody @Valid MeetingZoneInDTO meetingZoneInDTO){
        meetingZoneService.createMeetingZone(organizerId,experienceId,meetingZoneInDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Meeting Zone created"));
    }

    @PutMapping("/update-meeting-zone/{organizerId}/{experienceId}")
    public ResponseEntity updateMeetingZone(@PathVariable Integer organizerId,@PathVariable Integer experienceId,@RequestBody @Valid MeetingZoneInDTO meetingZoneInDTO){
        meetingZoneService.updateMeetingZone(organizerId,experienceId,meetingZoneInDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Meeting Zone updated"));
    }

    @DeleteMapping("/delete-meeting-zone/{organizerId}/{experienceId}")
    public ResponseEntity deleteMeetingZone(@PathVariable Integer organizerId,@PathVariable Integer experienceId) {
        meetingZoneService.deleteMeetingZone(organizerId,experienceId);
        return ResponseEntity.status(200).body(new ApiResponse("Meeting Zone deleted"));
    }

    @GetMapping("/get-meeting-zone-for-explorer-experience/{explorerId}/{experienceId}")
    public ResponseEntity getMeetingZoneForExplorerExperience(@PathVariable Integer explorerId,@PathVariable Integer experienceId){
        return ResponseEntity.status(200).body(meetingZoneService.getMeetingZoneForExplorerExperience(explorerId,experienceId));
    }
}
