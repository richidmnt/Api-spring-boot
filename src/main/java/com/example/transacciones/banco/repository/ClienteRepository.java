package com.example.transacciones.banco.repository;

import com.example.transacciones.banco.Dto.ClienteResponseDto;
import com.example.transacciones.banco.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity,Long> {
//    @Modifying
//    @Query("UPDATE Cliente c SET c.estado = False WHERE c.id = :id")
    @Query("SELECT c FROM ClienteEntity c WHERE c.estado = 'True'")
    List<ClienteEntity> findClientesActivos();
    @Query("SELECT c FROM ClienteEntity c WHERE c.id=:id and c.estado = 'True' ")
    Optional<ClienteEntity> findByIdAndEstadoTrue(@Param("id") Long id);

    Boolean existsByIdentificacion(String identificacion);
    

}
