package com.example.transacciones.banco.controller;

import com.example.transacciones.banco.Dto.ClienteRequestDto;
import com.example.transacciones.banco.Dto.ClienteResponseDto;
import com.example.transacciones.banco.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {
    private ClienteService clienteService;
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> clientes(){
        List<ClienteResponseDto> clientes = clienteService.encontrarTodosClientes();
        return ResponseEntity.ok(clientes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> buscarCliente(@PathVariable Long id){
        ClienteResponseDto clienteResponseDto = clienteService.buscarPorId(id);
        return ResponseEntity.ok(clienteResponseDto);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDto> guardarCliente( @Valid @RequestBody ClienteRequestDto clienteRequestDto){
        ClienteResponseDto clienteNuevo = clienteService.guardarCliente(clienteRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> actualizarCliente(@PathVariable Long id , @Valid @RequestBody ClienteRequestDto clienteRequestDto){
        ClienteResponseDto clienteResponseDto = clienteService.actualizarCliente(id,clienteRequestDto);
        return ResponseEntity.ok(clienteResponseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id){
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();

    }
}
