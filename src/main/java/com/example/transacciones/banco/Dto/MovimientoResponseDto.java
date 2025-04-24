package com.example.transacciones.banco.Dto;

import com.example.transacciones.banco.model.TipoMovimiento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoResponseDto {
    private Long id;
    private LocalDate fecha;
    private TipoMovimiento tipoMovimiento;
    private Double valor;
    private Long saldo;
    private CuentaResponseDto cuenta;
}
