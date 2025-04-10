package com.example.transacciones.banco.service.implement;

import com.example.transacciones.banco.Dto.CuentaRequestDto;
import com.example.transacciones.banco.Dto.CuentaResponseDto;
import com.example.transacciones.banco.exception.EntidadNotFoudException;
import com.example.transacciones.banco.exception.PersistenciaException;
import com.example.transacciones.banco.model.ClienteEntity;
import com.example.transacciones.banco.model.CuentaEntity;
import com.example.transacciones.banco.repository.ClienteRepository;
import com.example.transacciones.banco.repository.CuentaRepository;
import com.example.transacciones.banco.service.CuentaService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class CuentaServiceImpl implements CuentaService {
    private  CuentaRepository cuentaRepository;
    private ClienteRepository clienteRepository;
    private ModelMapper modelMapper;
    public CuentaServiceImpl(CuentaRepository cuentaRepository,ModelMapper modelMapper,ClienteRepository clienteRepository){
        this.cuentaRepository = cuentaRepository;
        this.modelMapper = modelMapper;
        this.clienteRepository = clienteRepository;

    }

    @Override
    public CuentaResponseDto obtenerCuentaPorId(Long id) {

        try{
            validateId(id);
            CuentaEntity  cuentaEntity = obtenerCuenta(id);
            return modelMapper.map(cuentaEntity,CuentaResponseDto.class);
        } catch (DataAccessException e) {
            throw new PersistenciaException("Ocurrio un error al obtener la cuenta",e);
        }

    }

    @Override
    @Transactional
    public CuentaResponseDto guardarCuenta(CuentaRequestDto cuentaRequestDto) {
        if(cuentaRequestDto == null){
            throw  new IllegalArgumentException("La cuenta no puede ser nula");
        }
        try{
            ClienteEntity clienteEntity = obtenerCliente(cuentaRequestDto.getClienteId());
            CuentaEntity cuentaEntity = modelMapper.map(cuentaRequestDto,CuentaEntity.class);
            cuentaEntity.setCliente(clienteEntity);
            return modelMapper.map(cuentaRepository.save(cuentaEntity),CuentaResponseDto.class);
        } catch (DataAccessException e) {
            throw new PersistenciaException("Ocurrio un error al intentar guardar la cuenta",e);
        }

    }

    @Override
    @Transactional
    public CuentaResponseDto actualizarCuenta(CuentaRequestDto cuentaRequestDto, Long id) {
        if(cuentaRequestDto == null){
            throw  new IllegalArgumentException("La cuenta no puede ser nula");
        }

        try{
            validateId(id);
            ClienteEntity clienteEntity = obtenerCliente(cuentaRequestDto.getClienteId());
            CuentaEntity cuentaExistente = obtenerCuenta(id);
            modelMapper.map(cuentaRequestDto,cuentaExistente);
            cuentaExistente.setCliente(clienteEntity);
            CuentaEntity cuentaActualizada = cuentaRepository.save(cuentaExistente);
            return modelMapper.map(cuentaActualizada,CuentaResponseDto.class);
        } catch (DataAccessException e) {
            throw new PersistenciaException("Ocurrio un error al actualizar la cuenta",e);
        }
    }

    @Override
    @Transactional
    public void eliminarCuenta(Long id) {
        try{
            validateId(id);
            CuentaEntity cuentaExistente = obtenerCuenta(id);
            cuentaExistente.desactivarCuenta();
        } catch (DataAccessException e) {
            throw new PersistenciaException("Ocurrio un error al eliminar la cuenta con id: "+id,e);
        }
    }

    @Override
    public List<CuentaResponseDto> obtenerTodasCuentas() {
        try{
            List<CuentaEntity> cuentasActivas = cuentaRepository.findByEstadoTrue();
            return  cuentasActivas
                    .stream()
                    .map((cuentaEntity -> modelMapper.map(cuentaEntity,CuentaResponseDto.class)))
                    .toList();
        }catch (DataAccessException e){
            throw  new PersistenciaException("Ocurrio un error al obtener las cuentas",e);
        }
    }

    @Override
    public List<CuentaResponseDto> obtenerCuentasPorCliente(Long id) {
        try{
            List<CuentaEntity> cuentasClientes = cuentaRepository.findByClienteId(id);
            return cuentasClientes
                    .stream()
                    .map(cuenta -> modelMapper.map(cuenta,CuentaResponseDto.class))
                    .toList();
        } catch (DataAccessException e) {
            throw new PersistenciaException("Ocurrio un error al obtener las cuentas",e);
        }
    }
    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido: " + id);
        }
    }
    private CuentaEntity obtenerCuenta(Long id){
        return  cuentaRepository.findByIdAndEstadoTrue(id).orElseThrow(()-> new EntidadNotFoudException("No se encontro el cliente con el id:"+id));

    }
    private ClienteEntity obtenerCliente(Long id){
        return clienteRepository.findByIdAndEstadoTrue(id).orElseThrow(()-> new EntidadNotFoudException("No se encontro el cliente con el id: "+id));

    }
}
