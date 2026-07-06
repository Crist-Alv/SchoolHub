package com.schoolhub.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "municipio")
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmunicipio")
    private Integer idmunicipio;

    private String municipio;

    @ManyToOne
    @JoinColumn(name = "iddep")
    private Departamento departamento;

}
