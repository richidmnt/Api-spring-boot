package com.example.transacciones.banco.Dto;

import com.example.transacciones.banco.model.Genero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteResponseDto {
    private  Long id;
    private String nombre;
    private Genero genero;
    private Short edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String estado;
}
