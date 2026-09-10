package com.schoolhub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@Table(name="persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombres;

    @NotEmpty
    private String apellidos;

    private String dui;

    private String nit;

    @NotEmpty
    private String nacionalidad;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @NotEmpty
    private String genero;

    @NotEmpty
    private String telefono;

    private String correo;

    @ManyToOne
    @JoinColumn(name = "estado_civil_id",  nullable = false)
    private EstadoCivil estadoCivil;

    @PrePersist
    @PreUpdate
    protected void normalizarCamposOpcionales() {

        if (dui != null && dui.isBlank()) {
            dui = null;
        }

        if (nit != null && nit.isBlank()) {
            nit = null;
        }

        if (correo != null && correo.isBlank()) {
            correo = null;
        }
    }
}
