package com.example.transacciones.banco.controller;

import com.example.transacciones.banco.Dto.CuentaRequestDto;
import com.example.transacciones.banco.Dto.CuentaResponseDto;
import com.example.transacciones.banco.service.CuentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cuentas")
public class CuentaController {
    private  CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<List<CuentaResponseDto>> obtenerCuentas(){
        List<CuentaResponseDto> cuentas = cuentaService.obtenerTodasCuentas();
        return ResponseEntity.ok(cuentas);
    }
    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<CuentaResponseDto>> obtenerCuentasPorCliente(@PathVariable Long id){
        List<CuentaResponseDto> cuentasClientes = cuentaService.obtenerCuentasPorCliente(id);
        return ResponseEntity.ok(cuentasClientes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CuentaResponseDto> obtenerCuentaPorId(@PathVariable Long id){
        CuentaResponseDto cuentaResponseDto = cuentaService.obtenerCuentaPorId(id);
        return ResponseEntity.ok(cuentaResponseDto);
    }
    @PostMapping
    public ResponseEntity<CuentaResponseDto> crearCuenta(@Valid @RequestBody CuentaRequestDto cuentaRequestDto){
        CuentaResponseDto cuentaResponseDto = cuentaService.guardarCuenta(cuentaRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaResponseDto> actualizarCuenta(@PathVariable Long id, @RequestBody CuentaRequestDto cuentaRequestDto){
        CuentaResponseDto cuentaResponseDto = cuentaService.actualizarCuenta(cuentaRequestDto,id);
        return ResponseEntity.ok(cuentaResponseDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCuenta(@PathVariable Long id){
        cuentaService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }
}
