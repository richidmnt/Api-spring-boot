package com.example.transacciones.banco.service.implement;

import com.example.transacciones.banco.Dto.ClienteRequestDto;
import com.example.transacciones.banco.Dto.ClienteResponseDto;
import com.example.transacciones.banco.exception.EntidadDuplicadaException;
import com.example.transacciones.banco.exception.EntidadNotFoudException;
import com.example.transacciones.banco.exception.PersistenciaException;
import com.example.transacciones.banco.model.ClienteEntity;
import com.example.transacciones.banco.repository.ClienteRepository;
import com.example.transacciones.banco.service.ClienteService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;
    private ModelMapper modelMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository,ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public ClienteResponseDto guardarCliente(ClienteRequestDto clienteRequestDto) {
        if(clienteRequestDto == null){
            throw  new IllegalArgumentException("El cliente no puede ser nulo");
        }
        if(clienteRepository.existsByIdentificacion(clienteRequestDto.getIdentificacion())){
            throw new EntidadDuplicadaException("Ya existe un cliente con esta identificación");
        }

        try{
            ClienteEntity clienteEntity = modelMapper.map(clienteRequestDto,ClienteEntity.class);
            return modelMapper.map(clienteRepository.save(clienteEntity),ClienteResponseDto.class);

        } catch (DataAccessException e) {
            throw new PersistenciaException("Error al guardar el cliente",e);
        }

    }
    @Override
    @Transactional
    public ClienteResponseDto actualizarCliente(Long id, ClienteRequestDto clienteRequestDto) {
            try{
                ClienteEntity clienteActualizar = modelMapper.map(clienteRequestDto,ClienteEntity.class);
                ClienteEntity clienteExistente = clienteRepository.findById(id).orElseThrow(()->new EntidadNotFoudException("No se encontro el cliente con el id:"+ id));
                modelMapper.getConfiguration().setSkipNullEnabled(true);
                modelMapper.map(clienteRequestDto, clienteExistente);
                clienteRepository.save(clienteExistente);
                return modelMapper.map(clienteExistente,ClienteResponseDto.class);
            }catch (DataAccessException e){
                throw  new PersistenciaException("Error al actualizar el cliente", e);
            }


    }

    @Override
    public ClienteResponseDto buscarPorId(Long id) {
        validateId(id);

        try{
            ClienteEntity clienteEntity = clienteRepository.findByIdAndEstadoTrue(id).orElseThrow(()->new EntidadNotFoudException("Este cliente no existe"));
            return modelMapper.map(clienteEntity,ClienteResponseDto.class);
        }catch (DataAccessException e){
            throw  new PersistenciaException("Error de acceso a datos al buscar cliente con ID:" + id,e);
        }


    }

    @Override
    @Transactional
    public void eliminarCliente(Long id) {
        try{
            validateId(id);
            ClienteEntity clienteEntity = clienteRepository.findById(id).orElseThrow(()->new EntidadNotFoudException("Este cliente no existe"));
            clienteEntity.desactivarCliente();
        } catch (DataAccessException e) {
            throw new PersistenciaException("Error al eliminar cliente",e);
        }

    }

    @Override
    public List<ClienteResponseDto> encontrarTodosClientes() {
        try{
            List<ClienteEntity> clientesExistentes = clienteRepository.findClientesActivos();
            if (clientesExistentes.isEmpty()) {
                throw new EntidadNotFoudException("No existen clientes activos.");
            }
            return clientesExistentes.stream().map((clienteExistente)->modelMapper.map(clienteExistente,ClienteResponseDto.class)).toList();
        } catch (DataAccessException e) {
            throw new PersistenciaException("Error al obtener clientes",e);
        }
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido: " + id);
        }
    }
}
