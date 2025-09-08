package com.example.owl.init;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.owl.model.Usuario;
import com.example.owl.model.Fic;
import com.example.owl.model.Caracteristicas;
import com.example.owl.model.Composicion;
import com.example.owl.model.ValorComposicion;
import com.example.owl.model.Calificacion;
import com.example.owl.model.PlazoDuracion;
import com.example.owl.model.PrincipalesInversiones;
import com.example.owl.model.RentabilidadVolatilidad;

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
        Usuario admin = new Usuario();
        admin.setNombre("Admin");
        admin.setCorreo("admin@ficapp.local");
        admin.setContrasenia("{noop}admin123");
        admin.setRol("ADMIN");

        Usuario user = new Usuario();
        user.setNombre("Usuario Demo");
        user.setCorreo("user@ficapp.local");
        user.setContrasenia("{noop}user123");
        user.setRol("USER");

        usuarioRepository.saveAll(Arrays.asList(admin, user));

        // ===== 2) Armar el agregado FIC completo =====
        Fic fic = new Fic();
        fic.setNombre("FIC Conservador Demo");
        fic.setGestor("Gestor S.A.");
        fic.setCustodio("Banco Custodio");
        fic.setPoliticaInversion("Renta fija de corto/mediano plazo, liquidez alta.");
        fic.setLink("https://ejemplo.local/fic-conservador");
        fic.setRiesgo("Moderado");
        fic.setRentabilidad("E.A. 8.7%");
        fic.setFechaCorte(Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        // --- Características ---
        Caracteristicas car = new Caracteristicas();
        car.setCalificacion("AA-");
        car.setTipoDeRenta("Renta Fija");
        car.setMoneda("COP");
        car.setActivo("Bonos TES / Corporativo");
        trySetFic(car, fic);

        // --- Composición y valores ---
        Composicion comp = new Composicion();
        comp.setTipoRenta("Renta Fija");
        comp.setPaisEmisor("Colombia");
        comp.setMoneda("COP");
        comp.setActivo("Mixto");
        trySetFic(comp, fic);

        ValorComposicion v1 = new ValorComposicion();
        v1.setDato("TES 2030");
        v1.setValor(45.5f);

        ValorComposicion v2 = new ValorComposicion();
        v2.setDato("Bonos corporativos AAA");
        v2.setValor(30.0f);

        ValorComposicion v3 = new ValorComposicion();
        v3.setDato("Caja/Liquidez");
        v3.setValor(24.5f);

        comp.setValoresComposiciones(new ArrayList<>(Arrays.asList(v1, v2, v3)));

        // --- Calificación histórica ---
        Calificacion calif = new Calificacion();
        calif.setCalificacion(4.5f);
        calif.setEntidadCalificadora("Value and Risk");
        calif.setFechaUltimaCalificacion(new Date());
        trySetFic(calif, fic);

        // --- Plazo / Duración ---
        PlazoDuracion pd1 = new PlazoDuracion();
        pd1.setInversionesPorPlazo("0-90 días");
        pd1.setParticipacion(40);
        trySetFic(pd1, fic);

        PlazoDuracion pd2 = new PlazoDuracion();
        pd2.setInversionesPorPlazo("91-360 días");
        pd2.setParticipacion(45);
        trySetFic(pd2, fic);

        PlazoDuracion pd3 = new PlazoDuracion();
        pd3.setInversionesPorPlazo(">360 días");
        pd3.setParticipacion(15);
        trySetFic(pd3, fic);

        // --- Principales inversiones ---
        PrincipalesInversiones inv1 = new PrincipalesInversiones();
        inv1.setEmisor("República de Colombia");
        inv1.setParticipacionDelEmisor("35%");
        trySetFic(inv1, fic);

        PrincipalesInversiones inv2 = new PrincipalesInversiones();
        inv2.setEmisor("Bancolombia");
        inv2.setParticipacionDelEmisor("12%");
        trySetFic(inv2, fic);

        // --- Rentabilidad / Volatilidad ---
        RentabilidadVolatilidad rv = new RentabilidadVolatilidad();
        rv.setTipoIndicador("Rentabilidad");
        rv.setTipoParticipacion("Clase A");
        rv.setUltimoMes(1.25);
        rv.setUltimos3Meses(3.6);
        rv.setUltimos6Meses(7.1);
        rv.setUltimos12Meses(13.4);
        rv.setUltimos2Anios(21.0);
        rv.setUltimos3Anios(26.2);
        trySetFic(rv, fic);

        // Agregar todo al FIC
        fic.setCaracteristicas(Arrays.asList(car));
        fic.setComposiciones(Arrays.asList(comp));
        fic.setCalificaciones(Arrays.asList(calif));
        fic.setPlazoDuraciones(Arrays.asList(pd1, pd2, pd3));
        fic.setPrincipalesInversiones(Arrays.asList(inv1, inv2));
        fic.setRentabilidadVolatilidades(Arrays.asList(rv));

        // ===== 3) Guardar solo el agregado raíz (CASCADE ALL hace el resto) =====
        ficRepository.save(fic);
    }

    // ---------- Helpers seguros para no romper si el método/campo no existe ----------
    private static void trySetFic(Object child, Fic fic) {
        try {
            child.getClass().getMethod("setFic", Fic.class).invoke(child, fic);
        } catch (Exception ignored) { }
    }

    private static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
