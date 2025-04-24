package com.example.transacciones.banco.Dto;

import java.time.LocalDate;

public class ReporteDto {
    private LocalDate fecha;
    private String nombreCliente;
    private String numberoCuentoa;
    private String tipoCuenta;
    private Double saldoIncial;
    private boolean estado;
    private Double movimiento;
    private Double saldoDisponible;
}
