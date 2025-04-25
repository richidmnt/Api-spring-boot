package com.example.transacciones.banco.service.implement;

import com.example.transacciones.banco.Dto.ReporteDto;
import com.example.transacciones.banco.controller.ReporteController;
import com.example.transacciones.banco.repository.ReporteRepository;
import com.example.transacciones.banco.service.ReporteService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

   private final ReporteRepository reporteRepository;

    public ReporteServiceImpl( ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;

    }


    @Override
    public List<ReporteDto> obtenerReporte(Long id, LocalDate desde, LocalDate hasta) {
        return  reporteRepository.obtenerReporteMovimientos(id,desde,hasta);
    }
}
