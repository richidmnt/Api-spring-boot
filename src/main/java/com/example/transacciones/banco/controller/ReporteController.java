package com.example.transacciones.banco.controller;

import com.example.transacciones.banco.Dto.ReporteDto;
import com.example.transacciones.banco.service.ReporteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/reportes")
public class ReporteController {
    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/cliente/{id}/movimientos")
    public ResponseEntity<List<ReporteDto>> obtenerReporte(@PathVariable Long id, LocalDate desde, LocalDate hasta){
        return ResponseEntity.ok(reporteService.obtenerReporte(id,desde,hasta));
    }
}
