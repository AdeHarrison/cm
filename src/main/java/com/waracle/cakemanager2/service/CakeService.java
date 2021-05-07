package com.waracle.cakemanager2.service;

import java.util.List;

import com.waracle.cakemanager2.dto.CakeDTO;
import com.waracle.cakemanager2.entity.Cake;

public interface CakeService {
    List<CakeDTO> getAllCakes();

    Cake createCake(CakeDTO cakeDTO);
}
