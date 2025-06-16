package org.example.deliverymatch.controller;

import lombok.AllArgsConstructor;
import org.example.deliverymatch.dto.UtilisateurDto;
import org.example.deliverymatch.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping ("api/v1/")
public class UtilisateurController {
    private  final UtilisateurService utilisateurService;
    @PostMapping("/add")
    public ResponseEntity<UtilisateurDto>createUtilisateur(@RequestBody UtilisateurDto utilisateurDto){
        UtilisateurDto utilisateurDto1=utilisateurService.createUtilisateur(utilisateurDto);
        return ResponseEntity.ok(utilisateurDto1);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDto>update(@PathVariable long id, @RequestBody UtilisateurDto utilisateurDto){
            UtilisateurDto utilisateurDto1=utilisateurService.updateUtilisateur(id, utilisateurDto);
            return ResponseEntity.ok(utilisateurDto1);

    }
    @GetMapping("/all")
    public ResponseEntity<List<UtilisateurDto>> getAllUtilisateur(){
        List<UtilisateurDto>utilisateurDtos=utilisateurService.getAllUtilisateurs();
        return ResponseEntity.ok(utilisateurDtos);
    }


}
