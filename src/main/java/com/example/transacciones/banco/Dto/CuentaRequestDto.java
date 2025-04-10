package com.example.transacciones.banco.Dto;

import com.example.transacciones.banco.model.TipoCuenta;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Valid
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaRequestDto {
    @NotBlank(message = "El número de cuenta es obligatorio")
    @Size(max = 10, message = "El número de cuenta no puede superar los 10 caracteres")
    private String numeroCuenta;

    @NotNull(message = "El tipo de cuenta es obligatorio")
    private TipoCuenta tipoCuenta;

    @NotNull(message = "El saldo inicial es obligatorio")
    @Min(value = 0, message = "El saldo inicial no puede ser negativo")
    private Double saldoInicial;

    private boolean estado;

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;
}
