package com.example.transacciones.banco.repository;

import com.example.transacciones.banco.model.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<MovimientoEntity,Long>{

}
