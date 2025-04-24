package com.example.transacciones.banco.service.implement;

import com.example.transacciones.banco.Dto.ReporteDto;
import com.example.transacciones.banco.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final ReporteService reporteService;

    public ReporteServiceImpl(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @Override
    public List<ReporteDto> obtenerReportePorFechasYCliente(Long id, LocalDate desde, LocalDate hasta) {
        return reporteService.obtenerReportePorFechasYCliente(id,desde,hasta);
    }
}
