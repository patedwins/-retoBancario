package com.reto.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

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
public class CuentaVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotNull
    private Integer idEntidad;
    @NotNull
    private String numCuenta;
    @NotNull
    private String tipo;
    @NotNull
    private BigDecimal saldoInicial;
    private Boolean estado;
}
