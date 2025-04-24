package com.example.transacciones.banco.service;

import com.example.transacciones.banco.Dto.MovimientoResponseDto;
import com.example.transacciones.banco.Dto.MovimientoRequestDto;

import java.util.List;

public interface MovimientoService {
    MovimientoResponseDto retiroMovimiento(MovimientoRequestDto movimientoRequestDto);
    MovimientoResponseDto DepositoMovimiento(MovimientoRequestDto movimientoRequestDto);
    MovimientoResponseDto obtenerMovimientoPorId(Long id);
    MovimientoResponseDto actualizarMovimiento(MovimientoRequestDto movimientoRequestDto, Long id);
    void eliminarMovimiento(Long id);
    List<MovimientoResponseDto> obtenerMovimientosPorCuenta(Long cuentaId);
    List<MovimientoResponseDto> obtenerMovimientosPorCliente(Long clienteId);
}
