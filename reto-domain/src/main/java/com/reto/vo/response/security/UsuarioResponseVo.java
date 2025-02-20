package com.reto.vo.response.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class UsuarioResponseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nombre;
    private String direccion;
    private Integer edad;
    private String contrasena;
    private String identificacion;
    private String telefono;
    private String genero;
    private Boolean estado;
}
