package com.example.transacciones.banco.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity extends PersonaEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contrasenia;
    private String estado;
    @OneToMany(mappedBy = "cliente" ,cascade = CascadeType.ALL )
    private List<CuentaEntity> cuentaEntities = new ArrayList<>();


    public void desactivarCliente(){
        this.estado = "False";
    }

    public boolean estaActivo(){
        return "True".equals(this.estado);
    }

}
