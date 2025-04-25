package com.example.transacciones.banco.controller;

import com.example.transacciones.banco.Dto.MovimientoRequestDto;
import com.example.transacciones.banco.Dto.MovimientoResponseDto;
import com.example.transacciones.banco.service.MovimientoService;
import org.apache.catalina.mapper.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/movimientos")
public class MovimientoController {
    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;

    }
    @GetMapping("/cuenta/{id}")
    public ResponseEntity<List<MovimientoResponseDto>  > obtenerMovimientoPorCuenta(@PathVariable Long id){
        List<MovimientoResponseDto> movimientosPorCuenta = movimientoService.obtenerMovimientosPorCuenta(id);
        return ResponseEntity.ok(movimientosPorCuenta);
    }
    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<MovimientoResponseDto>  > obtenerMovimientoPorCliente(@PathVariable Long id){
        List<MovimientoResponseDto> movimientosPorCliente = movimientoService.obtenerMovimientosPorCliente(id);
        return ResponseEntity.ok(movimientosPorCliente);
    }

    @PostMapping("/deposito")
    public ResponseEntity<MovimientoResponseDto> depositoMovimiento(@RequestBody MovimientoRequestDto movimientoRequestDto){
        MovimientoResponseDto movimientoResponseDto = movimientoService.DepositoMovimiento(movimientoRequestDto);
        return ResponseEntity.ok(movimientoResponseDto);
    }

    @PostMapping("/retiro")
    public ResponseEntity<MovimientoResponseDto>  retiroMovimiento(@RequestBody MovimientoRequestDto movimientoRequestDto){
        MovimientoResponseDto movimientoResponseDto = movimientoService.retiroMovimiento(movimientoRequestDto);
        return ResponseEntity.ok(movimientoResponseDto);
    }

}
