package com.schoolhub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="direccion")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "municipio_id")
    private Municipio municipio;

    private String zona;

    @ManyToOne
    @JoinColumn(name = "tipo_via_id")
    private TipoVia tipoVia;

    @NotNull
    private String direccion;

    private String referencia;

    private String lugar_nacimiento;
}
