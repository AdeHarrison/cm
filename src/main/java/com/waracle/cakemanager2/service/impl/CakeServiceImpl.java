package com.waracle.cakemanager2.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.waracle.cakemanager2.dto.CakeDTO;
import com.waracle.cakemanager2.entity.Cake;
import com.waracle.cakemanager2.repository.CakeRepository;
import com.waracle.cakemanager2.service.CakeService;

@Service
public class CakeServiceImpl implements CakeService {

    private final CakeRepository cakeRepository;

    public CakeServiceImpl(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    public List<CakeDTO> getCakes() {
        List<CakeDTO> cakeDTOS = new ArrayList<>();

        for (Cake cake : cakeRepository.findAll()) {
            cakeDTOS.add(new CakeDTO(cake));
        }

        return cakeDTOS;
    }

    public CakeDTO createCake(CakeDTO cakeDTO) {
        Cake newCake = cakeRepository.save(new Cake(cakeDTO.getTitle(), cakeDTO.getDescription(), cakeDTO.getImage()));

        return new CakeDTO(newCake);
    }
}
