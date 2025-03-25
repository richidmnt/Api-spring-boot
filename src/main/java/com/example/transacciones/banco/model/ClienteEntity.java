package com.example.transacciones.banco.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@EqualsAndHashCode(callSuper = true)
public class ClienteEntity extends PersonaEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contrasenia;
    private String estado;

    public ClienteEntity(String nombre, Genero genero, Short edad, String identificacion, String direccion, String telefono,String contrasenia,String estado) {
        super(nombre, genero, edad, identificacion, direccion, telefono);
        this.contrasenia = contrasenia;
        this.estado = estado;
    }
    public ClienteEntity() {
        super(); // Llama al constructor vacío de PersonaEntity
    }



    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void desactivarCliente(){
        this.estado = "False";
    }

    public boolean estaActivo(){
        return "True".equals(this.estado);
    }

}
