package com.example.transacciones.banco.repository;

import com.example.transacciones.banco.Dto.ReporteDto;
import com.example.transacciones.banco.model.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository <MovimientoEntity,Long>{
    @Query("SELECT new com.example.transacciones.bando.Dto.ReporteDto(" +
            "m.fecha, " +
            "m.cuenta.cliente.nombre, " +
            "m.cuenta.numeroCuenta, " +
            "m.cuenta.tipoCuenta, " +
            "m.cuenta.saldoInicial, " +
            "m.cuenta.estado, " +
            "m.valor, " +
            "m.saldo" +
            "FROM Movimiento m " +
            "WHERE m.cuenta.cliente.id = :clienteId AND m.fecha BETWEEN :desde AND :hasta")
    List<ReporteDto> obtenerReporteMovimientos(@Param("clienteId") Long clienteId,
                                               @Param("desde") LocalDate desde,
                                               @Param("hasta") LocalDate hasta);

}
