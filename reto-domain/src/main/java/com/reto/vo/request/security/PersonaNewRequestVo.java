package com.reto.vo.request.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * JwtResponse.
 *
 * @author patedwins
 * @version 1.0.0
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PersonaNewRequestVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String nombre;
    @NotNull
    private String genero;
    @NotNull
    private Integer edad;
    @NotNull
    private String identificacion;
    private String direccion;
    private String telefono;
    private Boolean estado;
}
