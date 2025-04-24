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
    private static  final String NOMBRE_REQUERIDO = "El nombre del cliente es requerido";
    private  static final String NOMBRE_LONGITUD = "El nombre no puede superar los 150 caracteres";
    private  static final String NOMBRE_FORMATO = "El nombre solo puede contener letras";

    @NotBlank(message = NOMBRE_REQUERIDO)
    @Size(max = 150, message = NOMBRE_LONGITUD)
    @Pattern(regexp = "^[A-Za-zÁáÉéÍíÓóÚúÑñ\\\\s]+$\"\n",message = NOMBRE_FORMATO)
    private String nombre;

    private Genero genero;

    @NotNull(message = "La edad del cliente es requerida")
    @Min(value = 0, message = "Su edad no puede ser negativa")
    @Max(value = 150, message = "Su edad no puede ser mayor de 150")
    private Short edad;

    @Size(min = 10, max = 15, message = "La identificacion debe tener entre 10 y 15 caracteres")
    @NotBlank(message = "La identificacion del cliente es requerida")
    private String identificacion;

    @NotBlank(message = "Dirección del cliente es requerida")
    private String direccion;

    @NotBlank(message = "Número de teléfono es requerido")
    @Size(min = 10,max = 10,message = " El Número de teléfono debe contener 10 dìgitos")
    @Pattern(regexp = "^{09}\\d{8}$")
    private String telefono;

    @NotBlank(message = "Contraseña de cliente es requerida")
    private String contrasenia;

    private String estado;

}
