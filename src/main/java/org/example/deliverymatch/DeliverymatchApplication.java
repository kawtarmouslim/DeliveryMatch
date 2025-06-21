package org.example.deliverymatch;

import org.example.deliverymatch.dto.AnnonceDto;
import org.example.deliverymatch.model.Annonce;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DeliverymatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliverymatchApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.createTypeMap(AnnonceDto.class, Annonce.class)
//                .addMappings(mapper -> {
//                    mapper.map(AnnonceDto::getIdAnnonce, Annonce::setIdAnnonce);
//                    mapper.map(AnnonceDto::getDepart, Annonce::setDepart);
//                    mapper.map(AnnonceDto::getEtape, Annonce::setEtape);
//                    mapper.map(AnnonceDto::getDestination, Annonce::setDestination);
//                    mapper.map(AnnonceDto::getCapacite, Annonce::setCapacite);
//                    mapper.map(AnnonceDto::getDimension, Annonce::setDimension);
//                    mapper.map(AnnonceDto::getTypeColis, Annonce::setTypeColis);

                    // Ignorer conducteur ici, car il est défini manuellement
//                });
        return modelMapper;
    }
}
