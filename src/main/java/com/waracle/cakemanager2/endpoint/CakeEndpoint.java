package com.waracle.cakemanager2.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemanager2.dto.CakeDTO;
import com.waracle.cakemanager2.service.CakeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping(path = "/")
public class CakeEndpoint {

    private final CakeService cakeService;

    public CakeEndpoint(CakeService cakeService) {
        this.cakeService = cakeService;
    }

    @GetMapping
    public List<CakeDTO> getCakes() {
        return cakeService.getCakes();
    }

    @GetMapping(path = "/cakes")
    public ResponseEntity<byte[]> downloadCakes() throws JsonProcessingException {
        String cakes = new ObjectMapper().writeValueAsString(getCakes());

        return ResponseEntity.ok()
                .header(CONTENT_DISPOSITION, "attachment; filename=cakes.json")
                .body(cakes.getBytes(StandardCharsets.UTF_8));
    }

    @PostMapping(path = "/cakes")
    @ResponseStatus(HttpStatus.CREATED)
    public CakeDTO createCake(@RequestBody CakeDTO cakeDTO) {
        return cakeService.createCake(cakeDTO);
    }
}
