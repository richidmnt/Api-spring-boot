package com.example.transacciones.banco.service;

import com.example.transacciones.banco.Dto.CuentaRequestDto;
import com.example.transacciones.banco.Dto.CuentaResponseDto;
import com.example.transacciones.banco.model.CuentaEntity;

import java.util.List;
import java.util.Optional;

public interface CuentaService {
    CuentaResponseDto obtenerCuentaPorId(Long id);
    CuentaResponseDto guardarCuenta(CuentaRequestDto cuentaRequestDto);
    CuentaResponseDto actualizarCuenta(CuentaRequestDto cuentaRequestDto,Long id);
    void eliminarCuenta(Long id );
    List<CuentaResponseDto> obtenerTodasCuentas();
    List<CuentaResponseDto> obtenerCuentasPorCliente(Long id);
}
