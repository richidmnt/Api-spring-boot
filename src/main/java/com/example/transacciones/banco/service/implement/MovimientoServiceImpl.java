package com.example.transacciones.banco.service.implement;

import com.example.transacciones.banco.Dto.CuentaResponseDto;
import com.example.transacciones.banco.Dto.MovimientoResponseDto;
import com.example.transacciones.banco.Dto.MovimientoRequestDto;
import com.example.transacciones.banco.exception.EntidadNotFoudException;
import com.example.transacciones.banco.exception.PersistenciaException;
import com.example.transacciones.banco.model.CuentaEntity;
import com.example.transacciones.banco.model.MovimientoEntity;
import com.example.transacciones.banco.repository.CuentaRepository;
import com.example.transacciones.banco.repository.MovimientoRepository;
import com.example.transacciones.banco.service.MovimientoService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {
    private final MovimientoRepository movimientoRepository;
    private final ModelMapper modelMapper;
    private final CuentaRepository cuentaRepository;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository,ModelMapper modelMapper,CuentaRepository cuentaRepository){
        this.movimientoRepository = movimientoRepository;
        this.modelMapper = modelMapper;
        this.cuentaRepository = cuentaRepository;
    }


    @Override
    @Transactional
    public MovimientoResponseDto retiroMovimiento(MovimientoRequestDto movimientoRequestDto) {
        if(movimientoRequestDto == null){
            throw new IllegalArgumentException("El movimiento no puede ser nulo");
        }
       try{
           CuentaEntity cuentaExistente = obtenerCuenta(movimientoRequestDto.getCuentaId());
           MovimientoEntity movimientoEntity = crearMovimiento(movimientoRequestDto,cuentaExistente,"Retiro");
           movimientoRepository.save(movimientoEntity);
           return modelMapper.map(movimientoEntity,MovimientoResponseDto.class);
       }catch (DataAccessException e){
           throw new PersistenciaException("Error al realizar el retiro",e);
       }
    }
    @Override
    @Transactional
    public MovimientoResponseDto DepositoMovimiento(MovimientoRequestDto movimientoRequestDto) {
        if(movimientoRequestDto == null){
            throw new IllegalArgumentException("El movimiento no puede ser nulo");
        }
        try{
            CuentaEntity cuentaExistente = obtenerCuenta(movimientoRequestDto.getCuentaId());
            MovimientoEntity movimientoEntity = crearMovimiento(movimientoRequestDto,cuentaExistente,"Deposito");
            movimientoRepository.save(movimientoEntity);
            return modelMapper.map(movimientoEntity,MovimientoResponseDto.class);
        }catch (DataAccessException e){
            throw new PersistenciaException("Error al realizar el doposito",e);
        }
    }

    @Override
    public MovimientoResponseDto obtenerMovimientoPorId(Long id) {
        return null;
    }

    @Override
    public MovimientoResponseDto actualizarMovimiento(MovimientoRequestDto movimientoRequestDto, Long id) {
        return null;
    }

    @Override
    public void eliminarMovimiento(Long id) {

    }

    @Override
    public List<MovimientoResponseDto> obtenerMovimientosPorCuenta(Long cuentaId) {
        try{
            List<MovimientoEntity> movimientos = movimientoRepository.findByCuentaId(cuentaId);
            if(movimientos.isEmpty()){
                return List.of();
            }
            return movimientos.stream().map((movimientoEntity)->modelMapper.map(movimientoEntity,MovimientoResponseDto.class)).toList();
        }catch (DataAccessException e){
            throw  new PersistenciaException("Error al obtener los movimientos",e);
        }
    }

    @Override
    public List<MovimientoResponseDto> obtenerMovimientosPorCliente(Long clienteId) {
       try{
           List<MovimientoEntity> movimientos = movimientoRepository.findByCuentaClienteId(clienteId);
           if(movimientos.isEmpty()){
               return List.of();
           }
           return movimientos.stream().map((movimientoEntity)->modelMapper.map(movimientoEntity,MovimientoResponseDto.class)).toList();
       }catch (DataAccessException e){
           throw  new PersistenciaException("Error al obtener los movimientos",e);
       }
    }

    private CuentaEntity obtenerCuenta(Long id){
        return cuentaRepository.findByIdAndEstadoTrue(id).orElseThrow(()-> new EntidadNotFoudException("No se encontro la cuenta con el id:"+id));
    }
    private void verificarSaldoDispoble(Double saldoActual,MovimientoRequestDto movmientoRequestDto){
           if(saldoActual < movmientoRequestDto.getValor()){
               throw  new IllegalArgumentException("No cuenta con saldo suficiente para realizar el movimiento");
           }

    }

    private Double realizarRetiro(Double saldoActual,MovimientoRequestDto movimientoRequestDto){
        verificarSaldoDispoble(saldoActual,movimientoRequestDto);
        return  saldoActual - movimientoRequestDto.getValor();
    }

    private Double realizarDeposito(Double saldoActual,MovimientoRequestDto movimientoRequestDto){
        return saldoActual + movimientoRequestDto.getValor();
    }

    private MovimientoEntity crearMovimiento(MovimientoRequestDto movimientoRequestDto, CuentaEntity cuentaExistente,String tipoMovimiento){
        MovimientoEntity movimientoEntity = new MovimientoEntity();
        movimientoEntity.setTipoMovimiento(movimientoRequestDto.getTipoMovimiento());
        movimientoEntity.setFecha(LocalDate.now());
        movimientoEntity.setValor(movimientoRequestDto.getValor());
        movimientoEntity.setCuenta(cuentaExistente);
        Double saldoActual = calcularSaldoActual(cuentaExistente);
        if(tipoMovimiento.equals("Deposito")){
            Double nuevoSaldo = realizarDeposito(saldoActual,movimientoRequestDto);
            movimientoEntity.setSaldo(nuevoSaldo);
            return movimientoEntity;
        }else{
            Double nuevoSaldo = realizarRetiro(saldoActual,movimientoRequestDto);
            movimientoEntity.setSaldo(nuevoSaldo);
            return movimientoEntity;
        }
    }

    private Double calcularSaldoActual(CuentaEntity cuenta) {
        List<MovimientoEntity> movimientos = cuenta.getMovimientoEntities();
        if (movimientos == null || movimientos.isEmpty()) {
            return cuenta.getSaldoInicial();
        }
        return movimientos.get(movimientos.size() - 1).getSaldo();
    }


}
