package org.example.deliverymatch.controller;

import lombok.AllArgsConstructor;
import org.example.deliverymatch.dto.AnnonceDto;
import org.example.deliverymatch.model.Annonce;
import org.example.deliverymatch.service.AnnonceService;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class AnnonceController {
    private final AnnonceService annonceService;

    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

   // @PreAuthorize("hasRole('CONDUCTEUR')")
    @PostMapping("/publier")
    public ResponseEntity<AnnonceDto>publishAnnonce(@RequestBody AnnonceDto annonceDto){
        AnnonceDto annonce = annonceService.publierAnnonce(annonceDto);
        return ResponseEntity.ok(annonce);
    }

    //@PreAuthorize("hasAnyRole('EXPEDITEUR', 'CONDUCTEUR')")
    @GetMapping("/search/{destination}/{typeColis}")
    public List<AnnonceDto> search(
            @PathVariable String destination,
            @PathVariable String typeColis

    ) {
        return annonceService.searchAnnonces(destination, typeColis);
    }

  //  @PreAuthorize("hasRole('ADMINISTRATEUR')")
    @PutMapping("anonce/{idAnonce}")
     public ResponseEntity<AnnonceDto> updateAnnonce(@PathVariable Long idAnonce, @RequestBody AnnonceDto annonceDto) {
        AnnonceDto annonceDto1=annonceService.updateAnnonce(idAnonce, annonceDto);
        return ResponseEntity.ok(annonceDto1);
    }

  //  @PreAuthorize("hasRole('ADMINISTRATEUR')")
    @DeleteMapping("/{idDelete}")
    public ResponseEntity<AnnonceDto> deleteAnnonce(@PathVariable Long idDelete) {
        annonceService.deleteAnnonce(idDelete);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/anonces")
    public ResponseEntity<List<AnnonceDto>> getAnnonces() {
        List<AnnonceDto> annonces = annonceService.getAnnonces();
        return ResponseEntity.ok(annonces);
    }
}
