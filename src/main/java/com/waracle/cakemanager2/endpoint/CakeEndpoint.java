package com.waracle.cakemanager2.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemanager2.dto.CakeDTO;
import com.waracle.cakemanager2.entity.Cake;
import com.waracle.cakemanager2.service.impl.CakeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class CakeEndpoint {

    private final CakeServiceImpl cakeService;

    public CakeEndpoint(CakeServiceImpl cakeService) {
        this.cakeService = cakeService;
    }

    @GetMapping
    public List<Cake> getAllCakes() {
        return cakeService.getAllCakes();
    }

    //    @GetMapping(path = "/cakes")
//    public ResponseEntity<Resource> downloadAllCakes() throws JsonProcessingException {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//
//        String cakesAsString = transformJsonToHumanReadable(getAllCakes());
//        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(cakesAsString.getBytes()));
//        ByteArrayResource resource2 = new ByteArrayResource(cakesAsString.getBytes());
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(cakesAsString.length())
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(resource2);
//    }
    @GetMapping(path = "/cakes")
    public List<String> downloadAllCakes() {
        return Arrays.asList("cake 11", "cake 12", "cake 13");
    }

    @PostMapping(path = "/cakes")
    public Cake createCake(@RequestBody CakeDTO cakeDTO) {
        return cakeService.createCake(cakeDTO);
    }

    private String transformJsonToHumanReadable(List<Cake> allCakes) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(allCakes);
    }
}
