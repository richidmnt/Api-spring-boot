package com.example.transacciones.banco.repository;

import com.example.transacciones.banco.model.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<CuentaEntity,Long> {
    Optional<CuentaEntity> findByIdAndEstadoTrue(Long id);
    List<CuentaEntity> findByEstadoTrue();
    List<CuentaEntity> findByClienteId(Long id);
}
