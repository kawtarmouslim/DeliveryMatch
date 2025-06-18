package org.example.deliverymatch.controller;

import lombok.AllArgsConstructor;
import org.example.deliverymatch.dto.DemandeDto;
import org.example.deliverymatch.service.DemandeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demande")
@AllArgsConstructor
public class DemandeController {
    private final DemandeService service;
    @PostMapping("/demander")
    public DemandeDto save(@RequestBody DemandeDto dto) {
        DemandeDto demandeDto=service.demander(dto);
        return demandeDto;

    }


}
