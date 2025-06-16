package org.example.deliverymatch.controller;

import lombok.AllArgsConstructor;
import org.example.deliverymatch.dto.AnnonceDto;
import org.example.deliverymatch.service.AnnonceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/")
public class AnnonceController {
    private AnnonceService annonceService;
    @PostMapping("/publier")
    public ResponseEntity<AnnonceDto>publishAnnonce(@RequestBody AnnonceDto annonceDto){
        AnnonceDto annonce = annonceService.publierAnnonce(annonceDto);
        return ResponseEntity.ok(annonce);
    }
}
