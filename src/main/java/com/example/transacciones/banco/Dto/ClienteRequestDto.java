package com.example.transacciones.banco.Dto;

import com.example.transacciones.banco.model.Genero;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Valid
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDto {
    @NotBlank(message = "El nombre del cliente es requerido")
    private String nombre;

    private Genero genero;

    @NotNull(message = "La edad del cliente es requerida")
    @Min(value = 0, message = "Su edad no puede ser negativa")
    @Max(value = 150, message = "Su edad no puede ser mayor de 150")
    @Digits(integer = 3, fraction = 0, message = "Su edad no puede contener decimales")
    private Short edad;

    @Size(min = 10, max = 15, message = "La identificacion debe tener entre 10 y 15 caracteres")
    @NotBlank(message = "La identificacion del cliente es requerida")
    private String identificacion;

    @NotBlank(message = "Dirección del cliente es requerida")
    private String direccion;

    @NotBlank(message = "Número de teléfono es requerido")
    @Size(min = 10,max = 10,message = " El Número de teléfono debe contener 10 dìgitos")
    private String telefono;

    @NotBlank(message = "Contraseña de cliente es requerida")
    private String contrasenia;

    private String estado;


}
