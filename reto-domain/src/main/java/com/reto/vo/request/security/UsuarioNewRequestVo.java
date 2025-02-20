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
public class UsuarioNewRequestVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String nombre;
    @NotNull
    private String direccion;
    @NotNull
    private Integer edad;
    @NotNull
    private String contrasena;
    @NotNull
    private String identificacion;
    @NotNull
    private String telefono;
    private String genero;
}
