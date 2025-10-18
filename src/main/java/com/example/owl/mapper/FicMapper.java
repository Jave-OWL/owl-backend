package com.example.owl.mapper;

import java.nio.file.spi.FileTypeDetector;

import com.example.owl.DTO.FicDTO;
import com.example.owl.model.Fic;

public class FicMapper {

    public static FicDTO toDTO(Fic fic) {
        return new FicDTO(

               fic.getId(),
               fic.getNombre_fic(),
               fic.getGestor(),
               fic.getCustodio(),
               fic.getFecha_corte(),
               fic.getPolitica_de_inversion(),
               fic.getTipo()
        );
    }

    public static Fic toEntity(FicDTO ficDTO) {
        Fic fic = new Fic();
        fic.setId(ficDTO.getId());
        fic.setNombre_fic(ficDTO.getNombre_fic());
        fic.setGestor(ficDTO.getGestor());
        fic.setCustodio(ficDTO.getCustodio());
        fic.setFecha_corte(ficDTO.getFecha_corte());
        fic.setPolitica_de_inversion(ficDTO.getPolitica_de_inversion());
        fic.setTipo(ficDTO.getTipo());
        return fic;
    }

}