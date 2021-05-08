package com.waracle.cakemanager2.service;

import java.util.List;

import com.waracle.cakemanager2.dto.CakeDTO;

public interface CakeService {
    List<CakeDTO> getCakes();

    CakeDTO createCake(CakeDTO cakeDTO);
}
