package org.example.deliverymatch.controller;

import lombok.AllArgsConstructor;
import org.example.deliverymatch.dto.AnnonceDto;
import org.example.deliverymatch.service.AnnonceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/search/{destination}/{typeColis}")
    public List<AnnonceDto> search(
            @PathVariable String destination,
            @PathVariable String typeColis

    ) {
        return annonceService.searchAnnonces(destination, typeColis);
    }
    @PutMapping("anonce/{idAnonce}")
     public ResponseEntity<AnnonceDto> updateAnnonce(@PathVariable Long idAnonce, @RequestBody AnnonceDto annonceDto) {
        AnnonceDto annonceDto1=annonceService.updateAnnonce(idAnonce, annonceDto);
        return ResponseEntity.ok(annonceDto1);
    }
}
