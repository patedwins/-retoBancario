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
public class EntidadRequestVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer id;
    @NotNull
    private String nombre;
    private Boolean estado;
}
