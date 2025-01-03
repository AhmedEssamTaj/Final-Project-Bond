package com.example.finalprojectbond.Controller;

import com.example.finalprojectbond.Api.ApiResponse;
import com.example.finalprojectbond.InDTO.ExplorerInDTO;
import com.example.finalprojectbond.OutDTO.BriefExplorerOutDTO;
import com.example.finalprojectbond.OutDTO.ExplorerOutDTO;
import com.example.finalprojectbond.Service.ExplorerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/explorer")
@RequiredArgsConstructor
public class ExplorerController {

    private final ExplorerService explorerService;

    @GetMapping("/get-all")
    public ResponseEntity<List<ExplorerOutDTO>> getAllExplorers() {
        return ResponseEntity.status(200).body(explorerService.getAllExplorers());
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerExplorer(@RequestBody @Valid ExplorerInDTO explorerInDTO) {
        explorerService.registerExplorer(explorerInDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Explorer registered successfully"));
    }

    @PutMapping("/update/{explorerId}")
    public ResponseEntity<ApiResponse> updateExplorer(@PathVariable Integer explorerId,@RequestBody @Valid ExplorerInDTO explorerInDTO) {
        explorerService.updateExplorer(explorerId,explorerInDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Explorer updated successfully"));
    }

    @DeleteMapping("/delete/{explorerId}")
    public ResponseEntity<ApiResponse> deleteExplorer(@PathVariable Integer explorerId) {
        explorerService.deleteExplorer(explorerId);
        return ResponseEntity.status(200).body(new ApiResponse("Explorer deleted successfully"));
    }


}
