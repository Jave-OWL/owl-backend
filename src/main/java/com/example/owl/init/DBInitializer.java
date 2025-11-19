package com.example.owl.init;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.owl.model.Calificacion;
import com.example.owl.model.Caracteristicas;
import com.example.owl.model.Composicion_Portafolio;
import com.example.owl.model.Fic;
import com.example.owl.model.Fic_Recomendado;
import com.example.owl.model.Plazo_Duracion;
import com.example.owl.model.Principales_Inversiones;
import com.example.owl.model.Rentabilidad_Historica;
import com.example.owl.model.Usuario;
import com.example.owl.model.Volatilidad_Historica;

import com.example.owl.repository.FicRepository;
import com.example.owl.repository.UsuarioRepository;


import com.example.owl.repository.Fic_RecomendadosRepository;

@Component
@Profile({"test","integration-test"})

public class DBInitializer implements CommandLineRunner {

    @Autowired
    private FicRepository ficRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Fic_RecomendadosRepository ficRecomendadoRepository;

    @Override
    public void run(String... args) {
        // ========= FIC (con TODAS las subentidades) =========
        Fic fic = new Fic();
        fic.setNombre_fic("CUBRIR BALANCEADO INTERNACIONAL");
        fic.setGestor("Fiduciaria Bogotá S.A.");
        fic.setCustodio("Cititrust");
        fic.setFecha_corte("2025-09-30");
        fic.setPolitica_de_inversion(
            "Es una alternativa de inversión con perfil de riesgo alto para un Inversionista agresivo conocedor del mercado, cuyo propósito es el crecimiento del capital en el largo plazo. La estrategia planteada es mantener altos rendimientos con gran dinamismo en las inversiones con el objetivo de generar rentabilidades competitivas frente a otros activos financieros o productos de especulación. El Fondo podrá integrar el portafolio con títulos de renta fija denominados en moneda nacional o en moneda extranjera inscritos en el Registro Nacional de Valores y Emisores - RNVE o listados en una bolsa de valores autorizada por la Superintendencia Financiera de Colombia o en un sistema de cotización de valores del extranjero o títulos participativos locales o del exterior. Igualmente podrá tener participación en fondos de inversión nacionales y extranjeros de acuerdo con el principio de mejor ejecución de la inversión para el Fondo de Inversión Colectiva."
        );
        fic.setTipo("Renta Fija");
        fic.setUrl("");

        // --- Calificaciones ---
        Calificacion cal = new Calificacion(
            "",                         
            null,                       
            "",                        
            null,                       
            fic
        );

        // --- Características ---
        Caracteristicas carac = new Caracteristicas(
            "Abierto con Pacto de permanencia",
            2456.83f,
            "2003-10-23",
            187877.67f,
            fic
        );

        // --- Composición de Portafolio ---
        List<Composicion_Portafolio> comp = List.of(
            new Composicion_Portafolio("activo", "Disponible", 0.144f, fic),
            new Composicion_Portafolio("activo", "Inversiones", 0.856f, fic),
            new Composicion_Portafolio("tipo_renta", "Cuentas", 0.144f, fic),
            new Composicion_Portafolio("tipo_renta", "Tasa Fija", 0.856f, fic),
            new Composicion_Portafolio("sector_economico", "Financiero", 0.144f, fic),
            new Composicion_Portafolio("sector_economico", "Entidades Públicas", 0.856f, fic),
            new Composicion_Portafolio("pais_emisor", "Colombia", 0.06f, fic),
            new Composicion_Portafolio("pais_emisor", "Estados Unidos", 0.94f, fic),
            new Composicion_Portafolio("moneda", "Pesos", 0.06f, fic),
            new Composicion_Portafolio("moneda", "Dólares", 0.9398f, fic),
            new Composicion_Portafolio("calificacion", "BRC1+", 0.0367f, fic),
            new Composicion_Portafolio("calificacion", "F1+", 0.0246f, fic),
            new Composicion_Portafolio("calificacion", "A+", 0.0822f, fic),
            new Composicion_Portafolio("calificacion", "AA+", 0.855f, fic),
            new Composicion_Portafolio("calificacion", "Sin calificación", 0.0016f, fic)
        );


        Volatilidad_Historica volI = new Volatilidad_Historica(
            "Inversionista tipo I",
            0.06875f,   
            0.10215f,   
            0.10005f,   
            0.0966f,    
            0.10608f,   
            0.11624f,   
            fic
        );

        Volatilidad_Historica volII = new Volatilidad_Historica(
            "Inversionista tipo II",
            0.06897f,
            0.10253f,
            0.10029f,
            0.09675f,
            0.10617f,
            0.1163f,
            fic
        );

      
        Rentabilidad_Historica renI = new Rentabilidad_Historica(
            "Inversionista tipo I",
            -0.22846f,   
            -0.09937f,  
            -0.11307f,  
            -0.04064f,  
            0.02474f,   
            -0.827f,     
            fic
        );

        Rentabilidad_Historica renII = new Rentabilidad_Historica(
            "Inversionista tipo II",
            -0.23945f,
            -0.10738f,
            -0.11915f,
            -0.04692f,
            0.02032f,
            -0.01047f,
            fic
        );

        
        List<Principales_Inversiones> invs = List.of(
            new Principales_Inversiones("ESTADOS UNIDOS DE AMERICA US TREASURY", 0.856f, fic),
            new Principales_Inversiones("CITIBANK NEW YORK", 0.0823f, fic),
            new Principales_Inversiones("BANCO DE BOGOTA SA", 0.0354f, fic),
            new Principales_Inversiones("BANCOLOMBIA S.A", 0.0236f, fic),
            new Principales_Inversiones("ALLFUNDS BANK S.A", 0.0017f, fic),
            new Principales_Inversiones("BANCO BBVA", 0.001f, fic),
            new Principales_Inversiones("BANCO DE BOGOTA NEW YORK AGENCY", 0.0001f, fic)
        );

     
        List<Plazo_Duracion> plazos = List.of(
            new Plazo_Duracion("1-180 DIAS", 0.91128f, fic),
            new Plazo_Duracion("181-360 DIAS", 0.0f, fic),
            new Plazo_Duracion("1 A 3 AÑOS", 0.0f, fic),
            new Plazo_Duracion("3 A 5 AÑOS", 0.08872f, fic),
            new Plazo_Duracion("MAS DE 5 AÑOS", 0.0f, fic)
        );

        fic.setCalificaciones(List.of(cal));
        fic.setCaracteristicas(List.of(carac));
        fic.setComposicion_portafolios(comp);
        fic.setVolatilidad_historicas(List.of(volI, volII));
        fic.setRentabilidad_historicas(List.of(renI, renII));
        fic.setPrincipales_inversiones(invs);
        fic.setPlazo_duraciones(plazos);

        fic = ficRepository.save(fic);


        // USUARIOS 
       
        Usuario miguel = new Usuario(
            "Miguel",
            "miguelbaronm@hotmail.com",
            "$2a$10$5/NpjRzAfq2zjSOIX/K0A.puUnXqpc.YJapN9G4km2e1EILiqrbfa",
            false,
            "2003-09-29",
            "Conservador"
        );
        miguel = usuarioRepository.save(miguel);

        Usuario admin = new Usuario(
            "Admin Test",
            "admin@owl.com",
            "$2a$10$GvSrX1HnWj6m8o9z3aTn2uEw2o5eG7C0GdQw3L7QF3aB1nD5f1n.6",
            true,
            "1990-01-01",
            "Agresivo"
        );
        admin = usuarioRepository.save(admin);

        // Asignar Fics Recomendados a Miguel
        if (ficRecomendadoRepository != null) {
            ficRecomendadoRepository.save(new Fic_Recomendado(miguel, fic));
           
        }
    }
}
