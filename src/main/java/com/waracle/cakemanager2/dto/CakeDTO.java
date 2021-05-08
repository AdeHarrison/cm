package com.waracle.cakemanager2.dto;

import com.waracle.cakemanager2.entity.Cake;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CakeDTO {

    private Integer employeeId;
    private String title;
    private String description;
    private String image;

    public CakeDTO(Cake cake) {
        this.employeeId = cake.getEmployeeId();
        this.title = cake.getTitle();
        this.description = cake.getDescription();
        this.image = cake.getImage();
    }
}
