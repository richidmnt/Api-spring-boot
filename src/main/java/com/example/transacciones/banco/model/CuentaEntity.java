package com.example.transacciones.banco.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cuenta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_cuenta", length = 10)
    private String numeroCuenta;
    @Column(name="tipo_cuenta")
    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta;
    @Column(name = "saldo_inicial")
    private Double saldoInicial;
    private boolean estado;
    @ManyToOne
    @JoinColumn(name = "cliente_id",nullable = false)
    private ClienteEntity cliente;
    @OneToMany(mappedBy = "cuenta",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<MovimientoEntity> movimientoEntities = new ArrayList<>();
    public void desactivarCuenta(){
        this.estado = false;
    }

}
