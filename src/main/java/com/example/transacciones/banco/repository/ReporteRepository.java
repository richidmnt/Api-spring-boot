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
public interface ReporteRepository extends JpaRepository<MovimientoEntity,Long> {
    @Query("SELECT new com.example.transacciones.banco.Dto.ReporteDto(" +
            "m.fecha, " +
            "c.cliente.nombre, " +
            "c.numeroCuenta, " +
            "CAST(c.tipoCuenta AS string), " +
            "c.saldoInicial, " +
            "CASE WHEN c.estado = true THEN true ELSE false END, " +
            "m.valor, " +
            "m.saldo) " +
            "FROM MovimientoEntity m " +
            "INNER JOIN m.cuenta c " +
            "WHERE c.cliente.id = :clienteId " +
            "AND m.fecha BETWEEN :desde AND :hasta " +
            "ORDER BY m.fecha")
    List<ReporteDto> obtenerReporteMovimientos(
            @Param("clienteId") Long clienteId,
            @Param("desde") LocalDate desde,
            @Param("hasta") LocalDate hasta
    );

}
