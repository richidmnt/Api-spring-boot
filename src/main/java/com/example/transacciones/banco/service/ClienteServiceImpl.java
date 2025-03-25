package com.example.transacciones.banco.service;

import com.example.transacciones.banco.Dto.ClienteRequestDto;
import com.example.transacciones.banco.Dto.ClienteResponseDto;
import com.example.transacciones.banco.model.ClienteEntity;
import com.example.transacciones.banco.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{

    private ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public ClienteResponseDto guardarCliente(ClienteRequestDto clienteRequestDto) {
        ClienteEntity clienteAguardar =  new ClienteEntity(clienteRequestDto.getNombre(),clienteRequestDto.getGenero(),clienteRequestDto.getEdad(),clienteRequestDto.getIdentificacion(),clienteRequestDto.getDireccion(),clienteRequestDto.getTelefono(),clienteRequestDto.getContrasenia(),clienteRequestDto.getEstado());
        ClienteEntity clienteDto =  clienteRepository.save(clienteAguardar);
        return  new ClienteResponseDto(clienteDto.getId(),clienteDto.getNombre(),clienteDto.getGenero(),clienteDto.getEdad(),clienteDto.getIdentificacion(),clienteDto.getDireccion(),clienteDto.getTelefono(),clienteDto.getEstado());

    }
    @Override
    @Transactional
    public ClienteResponseDto actualizarCliente(Long id, ClienteRequestDto clienteRequestDto) {
        if(clienteRepository.existsById(id)){
            ClienteEntity clienteActualizar =  new ClienteEntity(clienteRequestDto.getNombre(),clienteRequestDto.getGenero(),clienteRequestDto.getEdad(),clienteRequestDto.getIdentificacion(),clienteRequestDto.getDireccion(),clienteRequestDto.getTelefono(),clienteRequestDto.getContrasenia(),clienteRequestDto.getEstado());
            ClienteEntity clienteEntity = clienteRepository.findById(id).orElseThrow(()->new RuntimeException("No se encontro el cliente con el id:"+ id));
            clienteEntity.setNombre(clienteActualizar.getNombre());
            clienteEntity.setGenero(clienteActualizar.getGenero());
            clienteEntity.setEdad(clienteActualizar.getEdad());
            clienteEntity.setIdentificacion(clienteActualizar.getIdentificacion());
            clienteEntity.setDireccion(clienteActualizar.getDireccion());
            clienteEntity.setTelefono(clienteActualizar.getTelefono());
            clienteEntity.setContrasenia(clienteActualizar.getContrasenia());
            clienteEntity.setEstado(clienteActualizar.getEstado());
            clienteRepository.save(clienteEntity);
            return  new ClienteResponseDto(clienteEntity.getId(),clienteEntity.getNombre(),clienteEntity.getGenero(),clienteEntity.getEdad(),clienteEntity.getIdentificacion(),clienteEntity.getDireccion(),clienteEntity.getTelefono(),clienteEntity.getEstado());

        }
        return null;

    }

    @Override
    public ClienteResponseDto buscarPorId(Long id) {
        ClienteEntity clienteEntity = clienteRepository.findByIdAndEstadoTrue(id).orElseThrow(()->new RuntimeException("Este cliente no existe"));
        return new ClienteResponseDto(clienteEntity.getId(),clienteEntity.getNombre(),clienteEntity.getGenero(),clienteEntity.getEdad(),clienteEntity.getIdentificacion(),clienteEntity.getDireccion(),clienteEntity.getTelefono(),clienteEntity.getEstado());

    }

    @Override
    @Transactional
    public void eliminarCliente(Long id) {
        ClienteEntity clienteEntity = clienteRepository.findById(id).orElseThrow(()->new RuntimeException("Este cliente no existe"));
        clienteEntity.desactivarCliente();

    }

    @Override
    public List<ClienteResponseDto> encontrarTodosClientes() {
        List<ClienteEntity> clientesEntity = clienteRepository.findClientesActivos();
        return clientesEntity.stream().map((cliente)->new ClienteResponseDto(cliente.getId(),cliente.getNombre(),cliente.getGenero(),cliente.getEdad(),cliente.getIdentificacion(),cliente.getDireccion(),cliente.getTelefono(),cliente.getEstado())).toList();
    }
}
