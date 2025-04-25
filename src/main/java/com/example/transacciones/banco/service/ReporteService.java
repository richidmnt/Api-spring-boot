package com.example.transacciones.banco.service;

import com.example.transacciones.banco.Dto.ReporteDto;

import java.time.LocalDate;
import java.util.List;

public interface ReporteService {

    List<ReporteDto> obtenerReporte(Long id, LocalDate desde, LocalDate hasta);
}
