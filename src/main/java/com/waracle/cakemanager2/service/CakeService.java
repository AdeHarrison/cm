package com.waracle.cakemanager2.service;

import com.waracle.cakemanager2.dto.CakeDTO;
import com.waracle.cakemanager2.entity.Cake;

import java.util.List;

public interface CakeService {
    List<Cake> getAllCakes();

    Cake createCake(CakeDTO cakeDTO);
}
