package com.reto.vo;

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
public class ClienteVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer idPersona;
    private String clienteId;
    private String contrasena;
    private Boolean estado;
}
