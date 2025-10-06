package com.example.owl.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/db")
public class DbHealthController {
  private final JdbcTemplate jdbc;
  public DbHealthController(JdbcTemplate jdbc) { this.jdbc = jdbc; }

  @GetMapping("/health")
  public Map<String,Object> health() {
    Integer one = jdbc.queryForObject("select 1", Integer.class);
    Integer fics = jdbc.queryForObject("select count(*) from public.fic", Integer.class);
    Integer califs = jdbc.queryForObject("select count(*) from public.calificacion", Integer.class);
    return Map.of("select1", one, "fic_count", fics, "calificacion_count", califs);
  }
}