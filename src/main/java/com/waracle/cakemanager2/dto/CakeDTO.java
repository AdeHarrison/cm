package com.waracle.cakemanager2.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.waracle.cakemanager2.entity.Cake;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CakeDTO {
    @JsonIgnore
    private Integer id;
    private final String title;
    private final String description;
    private final String image;

    public CakeDTO(Cake cake) {
        this.id = cake.getEmployeeId();
        this.title = cake.getTitle();
        this.description = cake.getDescription();
        this.image = cake.getImage();
    }
}
