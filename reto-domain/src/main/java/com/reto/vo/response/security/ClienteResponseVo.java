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
public class ClienteResponseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private PersonaResponseVo persona;
    private String clienteId;
    private String contrasena;
    private Boolean estado;
}
