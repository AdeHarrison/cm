package com.waracle.cakemanager2.service;

import com.waracle.cakemanager2.dto.CakeDTO;

import java.util.List;

public interface CakeService {
    List<CakeDTO> getAllCakes();

    CakeDTO createCake(CakeDTO cakeDTO);
}
