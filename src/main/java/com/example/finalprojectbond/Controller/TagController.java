package com.example.finalprojectbond.Controller;

import com.example.finalprojectbond.Api.ApiResponse;
import com.example.finalprojectbond.InDTO.TagInDTO;
import com.example.finalprojectbond.Model.Tag;
import com.example.finalprojectbond.Service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;


        @GetMapping("/getAll")
        public ResponseEntity<List<Tag>> getAllTags() {
            return ResponseEntity.status(200).body(tagService.getAllTags());
        }

        @GetMapping("/getById/{tagId}")
        public ResponseEntity<Tag> getTagById(@PathVariable Integer tagId) {
            return ResponseEntity.status(200).body(tagService.getTagById(tagId));
        }

        @PostMapping("/add")
        public ResponseEntity<String> createTag(@RequestBody @Valid Tag tag) {
            tagService.createTag(tag);
            return ResponseEntity.status(200).body("Tag created successfully");
        }

        @PutMapping("/update/{tagId}")
        public ResponseEntity<String> updateTag(@PathVariable Integer tagId, @RequestBody @Valid Tag tag) {
            tagService.updateTag(tagId, tag);
            return ResponseEntity.status(200).body("Tag updated successfully");
        }

        @DeleteMapping("/delete/{tagId}")
        public ResponseEntity<String> deleteTag(@PathVariable Integer tagId) {
            tagService.deleteTag(tagId);
            return ResponseEntity.status(200).body("Tag deleted successfully");
        }
    //1
        @PostMapping("/assignTagsToExperience")
        public ResponseEntity<String> assignTagsToExperience(@RequestBody @Valid TagInDTO tagInDTO) {
            tagService.assignTagsToExperience(tagInDTO);
            return ResponseEntity.status(200).body("Tags assigned to experience successfully");
        }
    }

