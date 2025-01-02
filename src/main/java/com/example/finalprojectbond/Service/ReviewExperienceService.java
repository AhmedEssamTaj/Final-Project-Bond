package com.example.finalprojectbond.Service;

import com.example.finalprojectbond.Repository.ReviewExplorerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewExperienceService {

    private final ReviewExplorerRepository reviewExplorerRepository;
}
