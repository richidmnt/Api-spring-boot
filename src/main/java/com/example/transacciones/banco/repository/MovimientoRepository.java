package com.example.transacciones.banco.repository;

import com.example.transacciones.banco.model.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<MovimientoEntity,Long>{
    List<MovimientoEntity> findByCuentaId(Long id);
    List<MovimientoEntity> findByCuentaClienteId(Long id);
}
