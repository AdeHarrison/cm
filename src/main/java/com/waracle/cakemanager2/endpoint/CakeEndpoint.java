package com.waracle.cakemanager2.endpoint;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemanager2.dto.CakeDTO;
import com.waracle.cakemanager2.entity.Cake;
import com.waracle.cakemanager2.service.impl.CakeServiceImpl;

@RestController
@RequestMapping(path = "/")
public class CakeEndpoint {
    private static final ObjectMapper mapper = new ObjectMapper();

    private final CakeServiceImpl cakeService;

    public CakeEndpoint(CakeServiceImpl cakeService) {
        this.cakeService = cakeService;
    }

    @GetMapping
    public List<Cake> getCakes() {
        return cakeService.getAllCakes();
    }

    @GetMapping("/files")
    public ResponseEntity<byte[]> getFile() {
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cakes.json")
                .body("lksjdflskdfjl".getBytes(StandardCharsets.UTF_8));
    }

    @GetMapping(path = "/cakes")
    public ResponseEntity<byte[]> downloadCakes() throws JsonProcessingException {
        String cakes = mapper.writeValueAsString(getCakes());
        return ResponseEntity.ok()

                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cakes.json")
                .body(cakes.getBytes(StandardCharsets.UTF_8));
    }

    @PostMapping(path = "/cakes")
    public Cake createCake(@RequestBody CakeDTO cakeDTO) {
        return cakeService.createCake(cakeDTO);
    }
}
