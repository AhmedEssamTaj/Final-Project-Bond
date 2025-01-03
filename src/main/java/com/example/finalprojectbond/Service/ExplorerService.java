package com.example.finalprojectbond.Service;

import com.example.finalprojectbond.Api.ApiException;
import com.example.finalprojectbond.InDTO.ExplorerInDTO;
import com.example.finalprojectbond.Model.Explorer;
import com.example.finalprojectbond.Model.MyUser;
import com.example.finalprojectbond.OutDTO.BriefExplorerOutDTO;
import com.example.finalprojectbond.OutDTO.ExplorerOutDTO;
import com.example.finalprojectbond.Repository.AuthRepository;
import com.example.finalprojectbond.Repository.ExplorerRepository;
import com.example.finalprojectbond.Repository.ReviewExplorerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExplorerService {

    private final AuthRepository authRepository;
    private final ExplorerRepository explorerRepository;
    private final ReviewExplorerRepository reviewExplorerRepository;


    // GET All Explorers
    public List<ExplorerOutDTO> getAllExplorers() {
        List<Explorer> explorers = explorerRepository.findAll();
        return convertToExplorerDTO(explorers);

    }

    // Register new Explorer
    public void registerExplorer(ExplorerInDTO explorerInDTO) {
        MyUser myUser = new MyUser();
        myUser.setUsername(explorerInDTO.getUsername());
        myUser.setName(explorerInDTO.getName());
        myUser.setAge(explorerInDTO.getAge());
        myUser.setCity(explorerInDTO.getCity());
        myUser.setHealthStatus(explorerInDTO.getHealthStatus());
        myUser.setEmail(explorerInDTO.getEmail());
        myUser.setGender(explorerInDTO.getGender());
        myUser.setPhoneNumber(explorerInDTO.getPhoneNumber());
        myUser.setPhotoURL(explorerInDTO.getPhotoURL());
        myUser.setRole("EXPLORER");
        authRepository.save(myUser);
        // ==================================
        Explorer explorer = new Explorer();
        explorer.setMyUser(myUser);
        explorerRepository.save(explorer);
    }

    // Update existing Explorer
    public void updateExplorer(Integer explorerId,ExplorerInDTO explorerInDTO) {
        Explorer explorer= explorerRepository.findExplorerById(explorerId);
        MyUser myUser = authRepository.findMyUserById(explorerId);
        if( explorer == null || myUser == null) {
            throw new ApiException("explorer was not found");
        }
        myUser.setUsername(explorerInDTO.getUsername());
        myUser.setName(explorerInDTO.getName());
        myUser.setAge(explorerInDTO.getAge());
        myUser.setCity(explorerInDTO.getCity());
        myUser.setHealthStatus(explorerInDTO.getHealthStatus());
        myUser.setEmail(explorerInDTO.getEmail());
        myUser.setGender(explorerInDTO.getGender());
        myUser.setPhoneNumber(explorerInDTO.getPhoneNumber());
        myUser.setPhotoURL(explorerInDTO.getPhotoURL());
        myUser.setRole("EXPLORER");
        authRepository.save(myUser);
        explorer.setMyUser(myUser);
        explorerRepository.save(explorer);
    }

    // DELETE Explorer
    public void deleteExplorer(Integer explorerId) {
        Explorer explorer = explorerRepository.findExplorerById(explorerId);
        MyUser myUser = authRepository.findMyUserById(explorer.getMyUser().getId());
        if( explorer == null || myUser == null) {
            throw new ApiException("explorer was not found");
        }
        explorer.setMyUser(null);
        myUser.setExplorer(null);
        explorerRepository.delete(explorer);
        authRepository.delete(myUser);
    }

    // GET the brief information of the explorer
    public List<BriefExplorerOutDTO> getBriefExplorer(List <Explorer> explorers) {

        List<BriefExplorerOutDTO> briefExplorerOutDTO = new ArrayList<>();
        for (Explorer explorer : explorers) {
            briefExplorerOutDTO.add( new BriefExplorerOutDTO(explorer.getMyUser().getName(),
                    explorer.getMyUser().getAge()
                    ,explorer.getMyUser().getHealthStatus()
                    ,explorer.getMyUser().getGender()
                    ,explorer.getMyUser().getPhotoURL()));
        }

        return briefExplorerOutDTO;
    }

    public List<ExplorerOutDTO> convertToExplorerDTO(List<Explorer> explorers) {
        List<ExplorerOutDTO> explorerDTOs = new ArrayList<>();
        for (Explorer explorer : explorers) {
            explorerDTOs.add(new ExplorerOutDTO(explorer.getMyUser().getName(),
                    explorer.getMyUser().getAge(),
                    explorer.getMyUser().getCity(),
                    explorer.getMyUser().getHealthStatus(),
                    explorer.getMyUser().getEmail(),
                    explorer.getMyUser().getGender(),
                    explorer.getMyUser().getPhoneNumber(),
                    explorer.getMyUser().getPhotoURL(),
                    explorer.getMyUser().getRating())
            );
        }
        return explorerDTOs;
    }

}
