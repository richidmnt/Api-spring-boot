package com.example.transacciones.banco.Dto;

import com.example.transacciones.banco.model.TipoCuenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuentaResponseDto {
    private Long id;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private Double saldoInicial;
    private boolean estado;
    private ClienteResponseDto cliente;
}
