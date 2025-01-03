package com.example.finalprojectbond.OutDTO;

import com.example.finalprojectbond.Model.Experience;
import com.example.finalprojectbond.Model.Explorer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ApplicationOutDTO {
    private String description;

    private String tools;

    private String status;

}
