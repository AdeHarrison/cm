package com.waracle.cakemanager2.service.impl;

import com.waracle.cakemanager2.dto.CakeDTO;
import com.waracle.cakemanager2.entity.Cake;
import com.waracle.cakemanager2.repository.CakeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CakeServiceImpl implements com.waracle.cakemanager2.service.CakeService {

    private final CakeRepository cakeRepository;

    public CakeServiceImpl(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    public List<CakeDTO> getCakes() {
        // @formatter:off
        return cakeRepository.findAll()
                .stream().map(CakeDTO::new)
                    .collect(Collectors.toList());
        // @formatter:on;
    }

    public CakeDTO createCake(CakeDTO cakeDTO) {
        Cake newCake = cakeRepository.save(new Cake(cakeDTO.getTitle(), cakeDTO.getDescription(), cakeDTO.getImage()));

        return new CakeDTO(newCake);
    }
}
