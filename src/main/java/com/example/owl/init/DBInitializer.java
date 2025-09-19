package com.example.owl.init;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.owl.model.Calificacion;
import com.example.owl.model.Caracteristicas;
import com.example.owl.model.Composicion;
import com.example.owl.model.Fic;
import com.example.owl.model.PlazoDuracion;
import com.example.owl.model.PrincipalesInversiones;
import com.example.owl.model.RentabilidadVolatilidad;
import com.example.owl.model.Usuario;
import com.example.owl.model.ValorComposicion;
import com.example.owl.repository.FicRepository;
import com.example.owl.repository.UsuarioRepository;

@Component
public class DBInitializer implements CommandLineRunner {

    private final FicRepository ficRepository;
    private final UsuarioRepository usuarioRepository;

    public DBInitializer(FicRepository ficRepository, UsuarioRepository usuarioRepository) {
        this.ficRepository = ficRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (usuarioRepository.count() > 0 || ficRepository.count() > 0) return;

        // ===== 1) Usuarios =====
        Usuario admin = new Usuario("Admin", "admin@ficapp.local", "{noop}admin123", "ADMIN");
        Usuario user = new Usuario("Usuario Demo", "user@ficapp.local", "{noop}user123", "USER");

        usuarioRepository.saveAll(Arrays.asList(admin, user));

        // ===== 2) Armar el agregado FIC completo =====
        // ========== FIC 1 ==========
Fic fic1 = new Fic(
    "FIC Conservador Demo",
    "BancoDeBogota",
    "Bancolombia",
    "Renta fija de corto/mediano plazo, liquidez alta.",
    "https://ejemplo.local/fic-conservador",
    "Medio",
    "E.A. 8.7%",
    Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant())
);

Caracteristicas car1 = new Caracteristicas("AA-", "Renta Fija", "COP", "Bonos TES / Corporativo");
Composicion comp1 = new Composicion("Renta Fija", "Colombia", "COP", "Mixto");
comp1.setValoresComposiciones(Arrays.asList(
    new ValorComposicion("TES 2030", 45.5f),
    new ValorComposicion("Bonos corporativos AAA", 30.0f),
    new ValorComposicion("Caja/Liquidez", 24.5f)
));
Calificacion calif1 = new Calificacion(4.5f, "Value and Risk", new Date());
PlazoDuracion pd1a = new PlazoDuracion("0-90 días", 40);
PlazoDuracion pd1b = new PlazoDuracion("91-360 días", 45);
PlazoDuracion pd1c = new PlazoDuracion(">360 días", 15);
PrincipalesInversiones inv1a = new PrincipalesInversiones("República de Colombia", "35%");
PrincipalesInversiones inv1b = new PrincipalesInversiones("Bancolombia", "12%");
RentabilidadVolatilidad rv1 = new RentabilidadVolatilidad("Rentabilidad", "Clase A", 1.25, 3.6, 7.1, 13.4, 21.0, 26.2);

fic1.setCaracteristicas(Arrays.asList(car1));
fic1.setComposiciones(Arrays.asList(comp1));
fic1.setCalificaciones(Arrays.asList(calif1));
fic1.setPlazoDuraciones(Arrays.asList(pd1a, pd1b, pd1c));
fic1.setPrincipalesInversiones(Arrays.asList(inv1a, inv1b));
fic1.setRentabilidadVolatilidades(Arrays.asList(rv1));

ficRepository.save(fic1);


// ========== FIC 2 ==========
Fic fic2 = new Fic(
    "FIC Dinámico Internacional",
    "Davivienda",
    "Citibank",
    "Inversión en renta variable internacional, diversificación geográfica.",
    "https://ejemplo.local/fic-dinamico",
    "Alto",
    "E.A. 14.3%",
    Date.from(LocalDate.now().minusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant())
);

Caracteristicas car2 = new Caracteristicas("A+", "Renta Variable", "USD", "Acciones Globales / ETFs");
Composicion comp2 = new Composicion("Renta Variable", "EE.UU. / Europa", "USD", "Accionario");
comp2.setValoresComposiciones(Arrays.asList(
    new ValorComposicion("ETF S&P500", 50.0f),
    new ValorComposicion("ETF EuroStoxx50", 30.0f),
    new ValorComposicion("Caja/Liquidez", 20.0f)
));
Calificacion calif2 = new Calificacion(4.0f, "Fitch Ratings", new Date());
PlazoDuracion pd2a = new PlazoDuracion("0-90 días", 20);
PlazoDuracion pd2b = new PlazoDuracion("91-360 días", 30);
PlazoDuracion pd2c = new PlazoDuracion(">360 días", 50);
PrincipalesInversiones inv2a = new PrincipalesInversiones("Apple Inc.", "18%");
PrincipalesInversiones inv2b = new PrincipalesInversiones("Microsoft Corp.", "12%");
RentabilidadVolatilidad rv2 = new RentabilidadVolatilidad("Rentabilidad", "Clase B", 2.1, 4.5, 9.2, 15.8, 28.0, 35.1);

fic2.setCaracteristicas(Arrays.asList(car2));
fic2.setComposiciones(Arrays.asList(comp2));
fic2.setCalificaciones(Arrays.asList(calif2));
fic2.setPlazoDuraciones(Arrays.asList(pd2a, pd2b, pd2c));
fic2.setPrincipalesInversiones(Arrays.asList(inv2a, inv2b));
fic2.setRentabilidadVolatilidades(Arrays.asList(rv2));

ficRepository.save(fic2);


// ========== FIC 3 ==========
Fic fic3 = new Fic(
    "FIC Balanceado Plus",
    "Fiduoccidente",
    "Fiduoccidente",
    "Mezcla de renta fija y variable, con enfoque diversificado.",
    "https://ejemplo.local/fic-balanceado",
    "Alto",
    "E.A. 10.9%",
    Date.from(LocalDate.now().minusDays(3).atStartOfDay(ZoneId.systemDefault()).toInstant())
);

Caracteristicas car3 = new Caracteristicas("A", "Mixto", "COP/USD", "Bonos + Acciones LatAm");
Composicion comp3 = new Composicion("Mixto", "Latinoamérica", "COP/USD", "Diversificado");
comp3.setValoresComposiciones(Arrays.asList(
    new ValorComposicion("Bonos Brasil", 35.0f),
    new ValorComposicion("Acciones México", 40.0f),
    new ValorComposicion("Liquidez", 25.0f)
));
Calificacion calif3 = new Calificacion(3.9f, "Moody's", new Date());
PlazoDuracion pd3a = new PlazoDuracion("0-90 días", 25);
PlazoDuracion pd3b = new PlazoDuracion("91-360 días", 35);
PlazoDuracion pd3c = new PlazoDuracion(">360 días", 40);
PrincipalesInversiones inv3a = new PrincipalesInversiones("Vale S.A.", "15%");
PrincipalesInversiones inv3b = new PrincipalesInversiones("Grupo Bimbo", "10%");
RentabilidadVolatilidad rv3 = new RentabilidadVolatilidad("Rentabilidad", "Clase A", 1.8, 4.0, 8.7, 14.5, 23.6, 31.2);

fic3.setCaracteristicas(Arrays.asList(car3));
fic3.setComposiciones(Arrays.asList(comp3));
fic3.setCalificaciones(Arrays.asList(calif3));
fic3.setPlazoDuraciones(Arrays.asList(pd3a, pd3b, pd3c));
fic3.setPrincipalesInversiones(Arrays.asList(inv3a, inv3b));
fic3.setRentabilidadVolatilidades(Arrays.asList(rv3));

ficRepository.save(fic3);


// ========== FIC 4 ==========
Fic fic4 = new Fic(
    "FIC Liquidez Express",
    "Itau",
    "Davivienda",
    "Fondo de muy corto plazo con liquidez inmediata.",
    "https://ejemplo.local/fic-liquidez",
    "Bajo",
    "E.A. 6.1%",
    Date.from(LocalDate.now().minusDays(4).atStartOfDay(ZoneId.systemDefault()).toInstant())
);

Caracteristicas car4 = new Caracteristicas("AAA", "Renta Fija", "COP", "Depósitos a la vista / CDT");
Composicion comp4 = new Composicion("Renta Fija", "Colombia", "COP", "Corto Plazo");
comp4.setValoresComposiciones(Arrays.asList(
    new ValorComposicion("CDT Bancolombia", 55.0f),
    new ValorComposicion("Depósitos Vista", 45.0f)
));
Calificacion calif4 = new Calificacion(4.8f, "BRC Ratings", new Date());
PlazoDuracion pd4a = new PlazoDuracion("0-90 días", 85);
PlazoDuracion pd4b = new PlazoDuracion("91-360 días", 15);
PrincipalesInversiones inv4a = new PrincipalesInversiones("CDT Bancolombia", "40%");
PrincipalesInversiones inv4b = new PrincipalesInversiones("Davivienda", "25%");
RentabilidadVolatilidad rv4 = new RentabilidadVolatilidad("Rentabilidad", "Clase C", 0.8, 1.5, 2.3, 4.8, 6.7, 8.0);

fic4.setCaracteristicas(Arrays.asList(car4));
fic4.setComposiciones(Arrays.asList(comp4));
fic4.setCalificaciones(Arrays.asList(calif4));
fic4.setPlazoDuraciones(Arrays.asList(pd4a, pd4b));
fic4.setPrincipalesInversiones(Arrays.asList(inv4a, inv4b));
fic4.setRentabilidadVolatilidades(Arrays.asList(rv4));

ficRepository.save(fic4);


// ========== FIC 5 ==========
Fic fic5 = new Fic(
    "FIC Sostenible Verde",
    "Progresion",
    "HSBC",
    "Inversiones con enfoque en sostenibilidad y proyectos verdes.",
    "https://ejemplo.local/fic-verde",
    "Medio",
    "E.A. 9.2%",
    Date.from(LocalDate.now().minusDays(5).atStartOfDay(ZoneId.systemDefault()).toInstant())
);

Caracteristicas car5 = new Caracteristicas("AA", "Mixto", "USD", "Bonos verdes / Acciones ESG");
Composicion comp5 = new Composicion("Mixto", "Global", "USD", "ESG");
comp5.setValoresComposiciones(Arrays.asList(
    new ValorComposicion("Bonos verdes UE", 40.0f),
    new ValorComposicion("Acciones ESG", 35.0f),
    new ValorComposicion("Liquidez", 25.0f)
));
Calificacion calif5 = new Calificacion(4.2f, "Sustainalytics", new Date());
PlazoDuracion pd5a = new PlazoDuracion("0-90 días", 30);
PlazoDuracion pd5b = new PlazoDuracion("91-360 días", 40);
PlazoDuracion pd5c = new PlazoDuracion(">360 días", 30);
PrincipalesInversiones inv5a = new PrincipalesInversiones("Tesla Inc.", "12%");
PrincipalesInversiones inv5b = new PrincipalesInversiones("NextEra Energy", "8%");
RentabilidadVolatilidad rv5 = new RentabilidadVolatilidad("Rentabilidad", "Clase B", 1.6, 3.8, 7.9, 12.6, 20.1, 27.5);

fic5.setCaracteristicas(Arrays.asList(car5));
fic5.setComposiciones(Arrays.asList(comp5));
fic5.setCalificaciones(Arrays.asList(calif5));
fic5.setPlazoDuraciones(Arrays.asList(pd5a, pd5b, pd5c));
fic5.setPrincipalesInversiones(Arrays.asList(inv5a, inv5b));
fic5.setRentabilidadVolatilidades(Arrays.asList(rv5));

ficRepository.save(fic5);


// ========== FIC 6 ==========
Fic fic6 = new Fic(
    "FIC Infraestructura LATAM",
    "Bancolombia",
    "BTG Pactual",
    "Fondo especializado en proyectos de infraestructura latinoamericanos.",
    "https://ejemplo.local/fic-infra",
    "Alto",
    "E.A. 12.8%",
    Date.from(LocalDate.now().minusDays(6).atStartOfDay(ZoneId.systemDefault()).toInstant())
);

Caracteristicas car6 = new Caracteristicas("A", "Privado", "USD", "Deuda y Equity Infraestructura");
Composicion comp6 = new Composicion("Mixto", "LatAm", "USD", "Infraestructura");
comp6.setValoresComposiciones(Arrays.asList(
    new ValorComposicion("Carreteras Brasil", 50.0f),
    new ValorComposicion("Puertos México", 30.0f),
    new ValorComposicion("Energía Chile", 20.0f)
));
Calificacion calif6 = new Calificacion(3.8f, "Standard & Poor's", new Date());
PlazoDuracion pd6a = new PlazoDuracion("0-90 días", 15);
PlazoDuracion pd6b = new PlazoDuracion("91-360 días", 25);
PlazoDuracion pd6c = new PlazoDuracion(">360 días", 60);
PrincipalesInversiones inv6a = new PrincipalesInversiones("ISA Colombia", "14%");
PrincipalesInversiones inv6b = new PrincipalesInversiones("Grupo Aeroportuario", "11%");
RentabilidadVolatilidad rv6 = new RentabilidadVolatilidad("Rentabilidad", "Clase A", 2.3, 4.7, 9.4, 16.3, 27.2, 36.5);

fic6.setCaracteristicas(Arrays.asList(car6));
fic6.setComposiciones(Arrays.asList(comp6));
fic6.setCalificaciones(Arrays.asList(calif6));
fic6.setPlazoDuraciones(Arrays.asList(pd6a, pd6b, pd6c));
fic6.setPrincipalesInversiones(Arrays.asList(inv6a, inv6b));
fic6.setRentabilidadVolatilidades(Arrays.asList(rv6));

ficRepository.save(fic6);


// ========== FIC 7 ==========
Fic fic7 = new Fic(
    "FIC Tecnológico Global",
    "Credicorp",
    "Goldman Sachs",
    "Inversiones en compañías tecnológicas de alto crecimiento.",
    "https://ejemplo.local/fic-tech",
    "Alto",
    "E.A. 18.5%",
    Date.from(LocalDate.now().minusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant())
);

Caracteristicas car7 = new Caracteristicas("A-", "Renta Variable", "USD", "Acciones Tech Globales");
Composicion comp7 = new Composicion("Renta Variable", "Global", "USD", "Tecnología");
comp7.setValoresComposiciones(Arrays.asList(
    new ValorComposicion("Apple", 20.0f),
    new ValorComposicion("Google", 25.0f),
    new ValorComposicion("Amazon", 30.0f),
    new ValorComposicion("Liquidez", 25.0f)
));
Calificacion calif7 = new Calificacion(4.4f, "Morningstar", new Date());
PlazoDuracion pd7a = new PlazoDuracion("0-90 días", 10);
PlazoDuracion pd7b = new PlazoDuracion("91-360 días", 20);
PlazoDuracion pd7c = new PlazoDuracion(">360 días", 70);
PrincipalesInversiones inv7a = new PrincipalesInversiones("Apple Inc.", "20%");
PrincipalesInversiones inv7b = new PrincipalesInversiones("Alphabet Inc.", "15%");
RentabilidadVolatilidad rv7 = new RentabilidadVolatilidad("Rentabilidad", "Clase B", 3.1, 6.4, 12.3, 20.5, 35.7, 45.2);

fic7.setCaracteristicas(Arrays.asList(car7));
fic7.setComposiciones(Arrays.asList(comp7));
fic7.setCalificaciones(Arrays.asList(calif7));
fic7.setPlazoDuraciones(Arrays.asList(pd7a, pd7b, pd7c));
fic7.setPrincipalesInversiones(Arrays.asList(inv7a, inv7b));
fic7.setRentabilidadVolatilidades(Arrays.asList(rv7));

ficRepository.save(fic7);

// FIC 8
Fic fic8 = new Fic(
    "FIC Energético Sostenible",
    "Bancolombia",
    "JP Morgan",
    "Inversiones en energías renovables y proyectos sostenibles.",
    "https://ejemplo.local/fic-energy",
    "Medio",
    "E.A. 11.2%",
    Date.from(LocalDate.now().minusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant())
);

Caracteristicas car8 = new Caracteristicas("BBB+", "Renta Variable", "USD", "Energía Renovable Global");
Composicion comp8 = new Composicion("Renta Variable", "Global", "USD", "Energía");
comp8.setValoresComposiciones(Arrays.asList(
    new ValorComposicion("Tesla", 25.0f),
    new ValorComposicion("NextEra Energy", 30.0f),
    new ValorComposicion("Vestas Wind", 20.0f),
    new ValorComposicion("Liquidez", 25.0f)
));
Calificacion calif8 = new Calificacion(4.1f, "Morningstar", new Date());
PlazoDuracion pd8a = new PlazoDuracion("0-90 días", 15);
PlazoDuracion pd8b = new PlazoDuracion("91-360 días", 35);
PlazoDuracion pd8c = new PlazoDuracion(">360 días", 50);
PrincipalesInversiones inv8a = new PrincipalesInversiones("Tesla Inc.", "25%");
PrincipalesInversiones inv8b = new PrincipalesInversiones("NextEra Energy", "20%");
RentabilidadVolatilidad rv8 = new RentabilidadVolatilidad("Rentabilidad", "Clase A", 2.1, 5.3, 10.2, 16.4, 25.1, 33.8);

fic8.setCaracteristicas(Arrays.asList(car8));
fic8.setComposiciones(Arrays.asList(comp8));
fic8.setCalificaciones(Arrays.asList(calif8));
fic8.setPlazoDuraciones(Arrays.asList(pd8a, pd8b, pd8c));
fic8.setPrincipalesInversiones(Arrays.asList(inv8a, inv8b));
fic8.setRentabilidadVolatilidades(Arrays.asList(rv8));

ficRepository.save(fic8);

// FIC 9
Fic fic9 = new Fic(
    "FIC Infraestructura Regional",
    "Credicorp",
    "BNP Paribas",
    "Fondos destinados a proyectos de infraestructura en Latinoamérica.",
    "https://ejemplo.local/fic-infra",
    "Alto",
    "E.A. 13.7%",
    Date.from(LocalDate.now().minusDays(5).atStartOfDay(ZoneId.systemDefault()).toInstant())
);

Caracteristicas car9 = new Caracteristicas("A", "Renta Fija", "COP", "Infraestructura Regional");
Composicion comp9 = new Composicion("Renta Fija", "Latinoamérica", "COP", "Infraestructura");
comp9.setValoresComposiciones(Arrays.asList(
    new ValorComposicion("Bonos Carreteras", 35.0f),
    new ValorComposicion("Bonos Puertos", 25.0f),
    new ValorComposicion("Bonos Energía", 20.0f),
    new ValorComposicion("Liquidez", 20.0f)
));
Calificacion calif9 = new Calificacion(4.0f, "S&P", new Date());
PlazoDuracion pd9a = new PlazoDuracion("0-90 días", 10);
PlazoDuracion pd9b = new PlazoDuracion("91-360 días", 25);
PlazoDuracion pd9c = new PlazoDuracion(">360 días", 65);
PrincipalesInversiones inv9a = new PrincipalesInversiones("Bonos Autopistas", "30%");
PrincipalesInversiones inv9b = new PrincipalesInversiones("Bonos Energía Hidroeléctrica", "20%");
RentabilidadVolatilidad rv9 = new RentabilidadVolatilidad("Rentabilidad", "Clase C", 2.8, 6.0, 11.5, 18.3, 27.6, 38.1);

fic9.setCaracteristicas(Arrays.asList(car9));
fic9.setComposiciones(Arrays.asList(comp9));
fic9.setCalificaciones(Arrays.asList(calif9));
fic9.setPlazoDuraciones(Arrays.asList(pd9a, pd9b, pd9c));
fic9.setPrincipalesInversiones(Arrays.asList(inv9a, inv9b));
fic9.setRentabilidadVolatilidades(Arrays.asList(rv9));

ficRepository.save(fic9);

// FIC 10
Fic fic10 = new Fic(
    "FIC Salud Global",
    "Itau",
    "HSBC",
    "Inversiones en farmacéuticas y biotecnología a nivel mundial.",
    "https://ejemplo.local/fic-health",
    "Medio",
    "E.A. 10.9%",
    Date.from(LocalDate.now().minusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant())
);

Caracteristicas car10 = new Caracteristicas("BBB", "Renta Variable", "USD", "Salud y Biotecnología");
Composicion comp10 = new Composicion("Renta Variable", "Global", "USD", "Salud");
comp10.setValoresComposiciones(Arrays.asList(
    new ValorComposicion("Pfizer", 25.0f),
    new ValorComposicion("Moderna", 30.0f),
    new ValorComposicion("Johnson & Johnson", 20.0f),
    new ValorComposicion("Liquidez", 25.0f)
));
Calificacion calif10 = new Calificacion(3.9f, "Fitch", new Date());
PlazoDuracion pd10a = new PlazoDuracion("0-90 días", 20);
PlazoDuracion pd10b = new PlazoDuracion("91-360 días", 30);
PlazoDuracion pd10c = new PlazoDuracion(">360 días", 50);
PrincipalesInversiones inv10a = new PrincipalesInversiones("Pfizer Inc.", "25%");
PrincipalesInversiones inv10b = new PrincipalesInversiones("Moderna Inc.", "20%");
RentabilidadVolatilidad rv10 = new RentabilidadVolatilidad("Rentabilidad", "Clase B", 1.9, 4.7, 9.1, 14.8, 23.3, 30.9);

fic10.setCaracteristicas(Arrays.asList(car10));
fic10.setComposiciones(Arrays.asList(comp10));
fic10.setCalificaciones(Arrays.asList(calif10));
fic10.setPlazoDuraciones(Arrays.asList(pd10a, pd10b, pd10c));
fic10.setPrincipalesInversiones(Arrays.asList(inv10a, inv10b));
fic10.setRentabilidadVolatilidades(Arrays.asList(rv10));

ficRepository.save(fic10);

// FIC 11
Fic fic11 = new Fic(
    "FIC Agroindustrial Andino",
    "BancoDeBogota",
    "Citibank",
    "Fondos dirigidos a compañías agrícolas e industriales de la región andina.",
    "https://ejemplo.local/fic-agro",
    "Bajo",
    "E.A. 7.4%",
    Date.from(LocalDate.now().minusDays(20).atStartOfDay(ZoneId.systemDefault()).toInstant())
);

Caracteristicas car11 = new Caracteristicas("A-", "Renta Fija", "COP", "Agroindustria Andina");
Composicion comp11 = new Composicion("Renta Fija", "Andina", "COP", "Agroindustria");
comp11.setValoresComposiciones(Arrays.asList(
    new ValorComposicion("Bonos Cafeteros", 30.0f),
    new ValorComposicion("Bonos Azucareros", 25.0f),
    new ValorComposicion("Bonos Lácteos", 20.0f),
    new ValorComposicion("Liquidez", 25.0f)
));
Calificacion calif11 = new Calificacion(3.7f, "Moody's", new Date());
PlazoDuracion pd11a = new PlazoDuracion("0-90 días", 25);
PlazoDuracion pd11b = new PlazoDuracion("91-360 días", 40);
PlazoDuracion pd11c = new PlazoDuracion(">360 días", 35);
PrincipalesInversiones inv11a = new PrincipalesInversiones("Bonos Cafeteros", "30%");
PrincipalesInversiones inv11b = new PrincipalesInversiones("Bonos Azucareros", "20%");
RentabilidadVolatilidad rv11 = new RentabilidadVolatilidad("Rentabilidad", "Clase D", 1.2, 3.5, 7.8, 12.1, 18.6, 25.4);

fic11.setCaracteristicas(Arrays.asList(car11));
fic11.setComposiciones(Arrays.asList(comp11));
fic11.setCalificaciones(Arrays.asList(calif11));
fic11.setPlazoDuraciones(Arrays.asList(pd11a, pd11b, pd11c));
fic11.setPrincipalesInversiones(Arrays.asList(inv11a, inv11b));
fic11.setRentabilidadVolatilidades(Arrays.asList(rv11));

ficRepository.save(fic11);

// FIC 12
Fic fic12 = new Fic(
    "FIC Inmobiliario Urbano",
    "Davivienda",
    "BlackRock",
    "Fondos de inversión en bienes raíces comerciales y residenciales.",
    "https://ejemplo.local/fic-realestate",
    "Bajo",
    "E.A. 14.6%",
    Date.from(LocalDate.now().minusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant())
);

Caracteristicas car12 = new Caracteristicas("BBB", "Renta Variable", "USD", "Inmobiliario Urbano");
Composicion comp12 = new Composicion("Renta Variable", "Global", "USD", "Inmobiliario");
comp12.setValoresComposiciones(Arrays.asList(
    new ValorComposicion("REIT Comerciales", 40.0f),
    new ValorComposicion("REIT Residenciales", 30.0f),
    new ValorComposicion("Fondos Oficinas", 15.0f),
    new ValorComposicion("Liquidez", 15.0f)
));
Calificacion calif12 = new Calificacion(4.2f, "Morningstar", new Date());
PlazoDuracion pd12a = new PlazoDuracion("0-90 días", 12);
PlazoDuracion pd12b = new PlazoDuracion("91-360 días", 28);
PlazoDuracion pd12c = new PlazoDuracion(">360 días", 60);
PrincipalesInversiones inv12a = new PrincipalesInversiones("REIT Comerciales", "40%");
PrincipalesInversiones inv12b = new PrincipalesInversiones("REIT Residenciales", "25%");
RentabilidadVolatilidad rv12 = new RentabilidadVolatilidad("Rentabilidad", "Clase A", 3.0, 6.1, 12.4, 19.8, 28.9, 39.7);

fic12.setCaracteristicas(Arrays.asList(car12));
fic12.setComposiciones(Arrays.asList(comp12));
fic12.setCalificaciones(Arrays.asList(calif12));
fic12.setPlazoDuraciones(Arrays.asList(pd12a, pd12b, pd12c));
fic12.setPrincipalesInversiones(Arrays.asList(inv12a, inv12b));
fic12.setRentabilidadVolatilidades(Arrays.asList(rv12));

ficRepository.save(fic12);

    }

    // ---------- Helper seguro para no romper si el método/campo no existe ----------
    private static void trySetFic(Object child, Fic fic) {
        try {
            child.getClass().getMethod("setFic", Fic.class).invoke(child, fic);
        } catch (Exception ignored) { }
    }
}
