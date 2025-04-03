package com.example.transacciones.banco.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="movimiento")
public class MovimientoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    @Column(name = "tipo_movimiento")
    @Enumerated
    private TipoMovimiento tipoMovimiento;
    private Double valor;
    private  Double saldo;
    @ManyToOne
    @JoinColumn(name="cuenta_id")
    private CuentaEntity cuenta;

}
