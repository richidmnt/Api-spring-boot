package com.example.transacciones.banco.service;

import com.example.transacciones.banco.Dto.ClienteRequestDto;
import com.example.transacciones.banco.Dto.ClienteResponseDto;

import java.util.List;

public interface ClienteService {
    ClienteResponseDto guardarCliente(ClienteRequestDto clienteRequestDto);

    ClienteResponseDto actualizarCliente(Long id, ClienteRequestDto clienteRequestDto);

    ClienteResponseDto buscarPorId(Long id);
    void eliminarCliente(Long id);
    List<ClienteResponseDto> encontrarTodosClientes();

}
