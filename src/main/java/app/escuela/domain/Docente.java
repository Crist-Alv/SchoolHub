package app.escuela.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@Table(name="docente")
public class Docente {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iddocente;

    @NotEmpty
    private String nombresd;

    @NotEmpty
    private String apellidosd;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechanac;

    @NotEmpty
    private String generod;

    @NotEmpty
    private String especialidad;

    @NotEmpty
    private String nacionalidad;

    @NotEmpty
    private String depdocente;

    @NotEmpty
    private String municipiodocente;

    @NotEmpty
    private String telefono;

    @NotEmpty
    private String correo;

    @NotEmpty
    private String direccion;

    @NotNull
    private Integer estado;

}
