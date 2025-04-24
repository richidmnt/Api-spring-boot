package com.example.transacciones.banco.Dto;

import com.example.transacciones.banco.model.TipoMovimiento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Valid
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimientoRequestDto {
    private TipoMovimiento tipoMovimiento;
    @NotBlank(message = "El valor es requerido")
    @Digits(integer = 10, fraction = 2,message = "El valor debe tener 2 decimales")
    @Min(value = 0, message = "El valor no puede ser negativo")
    private Double valor;
    private  Long cuentaId;
}
