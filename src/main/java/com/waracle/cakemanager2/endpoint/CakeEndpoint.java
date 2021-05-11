package com.waracle.cakemanager2.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemanager2.dto.CakeDTO;
import com.waracle.cakemanager2.service.CakeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class CakeEndpoint {

    private final ObjectMapper mapper;
    private final CakeService cakeService;

    public CakeEndpoint(ObjectMapper mapper, CakeService cakeService) {
        this.mapper = mapper;
        this.cakeService = cakeService;
    }

    @GetMapping
    public List<CakeDTO> getCakes() {
        return cakeService.getCakes();
    }

    @GetMapping(path = "/cakes")
    public ResponseEntity<byte[]> downloadCakes() throws JsonProcessingException {
        String cakes = mapper.writeValueAsString(getCakes());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cakes.json")
                .body(cakes.getBytes(StandardCharsets.UTF_8));
    }

    @PostMapping(path = "/cakes")
    @ResponseStatus(HttpStatus.CREATED)
    public CakeDTO createCake(@RequestBody CakeDTO cakeDTO) {
        return cakeService.createCake(cakeDTO);
    }
}
