package com.example.finalprojectbond.InDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class TagInDTO {
    private Integer experienceId;
    private List<Integer> tagIds;
}
